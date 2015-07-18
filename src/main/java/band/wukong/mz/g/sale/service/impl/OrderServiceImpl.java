package band.wukong.mz.g.sale.service.impl;

import band.wukong.mz.base.bean.Period;
import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.customer.bean.Customer;
import band.wukong.mz.g.customer.service.CustomerService;
import band.wukong.mz.g.privilege.bean.User;
import band.wukong.mz.g.privilege.service.UserService;
import band.wukong.mz.g.sale.OutOfStockException;
import band.wukong.mz.g.sale.bean.Cart;
import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.bean.Order;
import band.wukong.mz.g.sale.bean.Sku4Item;
import band.wukong.mz.g.sale.dao.ItemDao;
import band.wukong.mz.g.sale.dao.OrderDao;
import band.wukong.mz.g.sale.service.*;
import band.wukong.mz.g.sku.bean.SkuMore;
import band.wukong.mz.g.sku.bean.SkuMoreView;
import band.wukong.mz.g.sku.dao.SkuMoreDao;
import band.wukong.mz.g.sku.service.SkuMoreViewService;
import band.wukong.mz.g.sku.service.SkuService;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * As you see....
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "orderService")
public class OrderServiceImpl implements OrderService {
    private static final Log log = Logs.get();

    @Inject
    private OrderDao orderDao;

    @Inject
    private ItemDao itemDao;

    @Inject
    private CartService cartService;

    @Inject
    private SkuService skuService;

    @Inject("skuMoreViewService")
    private SkuMoreViewService smvService;

    @Inject
    private UserService userService;

    @Inject
    private CustomerService custService;

    @Override
    public Order find(long id) {
        return orderDao.find(id);
    }

    @Override
    public QueryResult listDetail(String qcondOnCust, Period p, User u, int pageNum, int pageSize) {
        QueryResult qr = orderDao.list(qcondOnCust, p, u, pageNum, pageSize);
        List<Order> olist = qr.getList(Order.class);
        for (Order o : olist) {
            List<Item> items = itemDao.listWithSkuByOrder(o.getId());
            o.setItems(items);
        }
        return qr;
    }

    @Override
    public void pay(final Cart[] carts, final Long userId) {
        if (!OrderServiceValidator.pay(carts, userId)) {
            throw new IllegalParameterException();
        }

        //1、查询用户是否存在、营业员是否存在。都存在才下一步
        if (null == userService.find(userId)) {
            log.error("Could not find user: userId=" + userId);
            throw new AppRuntimeException("找不到该系统用户。");
        }
        final Customer cust = custService.find(carts[0].getCustId());
        if (null == cust) {
            log.error("Could not find customer: custId=" + custService.find(carts[0].getCustId()));
            throw new AppRuntimeException("找不到该客户。");
        }

        Trans.exec(new Atom() {
            public void run() {
                long nowPaymentClothing = cust.getPaymentClothing();
                long newPaymentClothing = cust.getPaymentClothing();

                //2、查询产品的库存是否都足够
                //3、下单
                //5、更新库存
                Order o = new Order();
                o.setUserId(userId);
                o.setCustId(carts[0].getCustId());
                o.setDtime(new Date());
                List<Item> items = new ArrayList<Item>();
                for (Cart c : carts) {
                    //查出对应的skuMore
                    SkuMoreView smv = smvService.find(c.getSkuMoreId());
                    //库存不够就抛出异常
                    if (smv.getCount() < c.getCount()) {
                        throw new OutOfStockException("库存不够");
                    }
                    Item item = assembleItem(smv, c, nowPaymentClothing);
                    items.add(item);
                    //计算paymentClothing
                    if (DiscountRule.discount(smv.getCateCode(), nowPaymentClothing) < 1) {
                        newPaymentClothing += item.getPayment();
                    }
                    //减少库存
                    skuService.reduceStock(smv.getSkuMoreId(), c.getCount());
                }
                o.setItems(items);
                orderDao.insertWithItems(o);

                // 4、看下单内容中有无服装类。看cust是不是非会员顾客，是会员才会更新paymentclothing
                // 有就更新用户表服装paymentClothing的值，新值为原有值+新单中服装类商品的成交价
                if (o.getCustId() != Customer.NON_MEMBER_ID && newPaymentClothing > nowPaymentClothing) {
                    cust.setPaymentClothing(newPaymentClothing);
                    custService.updatePayment(cust);
                }

                //6、删除购物车中已下单的产品
                for (Cart c : carts) {
                    cartService.rm(userId, cust.getId(), c.getSkuMoreId());
                }
            }
        });
    }

    @Override
    public Item findItemWithOrder(long itemId) {
        if (itemId <= 0) {
            throw new IllegalParameterException();
        }

        return itemDao.findWithOrder(itemId);
    }

    @Override
    public Item returnItem(final Item i) {
        final Item[] item4return = {null};

        //1、check要退货的数量是否不大于购买数量。如果大于则抛出异常
        Item item4buy = null;
        int item4buy_dcount_bought = 0;
        int item4buy_dcount_returned = 0;
        List<Item> items = itemDao.listSameItems(i.getId());
        for (Item item : items) {
            if (Item.STATE_OK.equals(item.getState())) {
                if (item4buy_dcount_bought != 0) {
                    throw new AppRuntimeException();
                }
                item4buy_dcount_bought = item.getDcount();
                item4buy = item;

            } else if (Item.STATE_RETURN.equals(item.getState())) {
                item4buy_dcount_returned += item.getDcount();
            }
        }
        if (item4buy_dcount_bought < item4buy_dcount_returned) {
            throw new AppRuntimeException("尼玛！退货数量比购买数量还多？");

        } else if (item4buy_dcount_bought + item4buy_dcount_returned <= 0) {
            throw new AppRuntimeException("尼玛！之前不是退过了吗怎么又退？退货总数量大于购买数量！");
        }
        if (null == item4buy) {
            String msg = "SHIT！这个item怎么只有退货没有购买！";
            log.error(msg);
            log.error("入参：" + i);
            throw new AppRuntimeException(msg);
        }

        final Item item4buy_link = item4buy;
        Trans.exec(new Atom() {
            public void run() {
                //2、insert item：退货的数量（负数），计算新的交易金额（负数），状态为'退货'，同时保存退货的时间和说明
                item4buy_link.setId(0);
                item4buy_link.setDcount(-i.getDcount());
                item4buy_link.setPayment(item4buy_link.getDprice() * item4buy_link.getDcount());  //取负数
                item4buy_link.setReturnTime(new Date());
                item4buy_link.setReturnUserId(i.getReturnUserId());
                item4buy_link.setReturnReason(i.getReturnReason());
                item4buy_link.setReturnDesc(i.getReturnDesc());
                item4buy_link.setState(Item.STATE_RETURN);
                item4return[0] = itemDao.insert(item4buy_link);

                //3、恢复库存
                skuService.addStock(item4return[0].getSkuMoreId(), i.getDcount());

                //4、如果商品是服装类，就更新用户表服装paymentClothing的值，新值为原有值+新单中服装类商品的成交价（成交价为负值）
                //4.1、如果是非会员客户就不用算paymentClothing了
                Order order = orderDao.find(item4return[0].getOid());
                if (order.getCustId() != Customer.NON_MEMBER_ID) {
                    Customer cust = custService.find(order.getCustId());
                    cust.setPaymentClothing(cust.getPaymentClothing() + item4return[0].getPayment());
                    custService.updatePayment(cust);
                }
            }
        });
        //CASE arch:layer 这里对item的操作，应该放在哪？OrderService里，还是ItemService里？
        //还是orderService中。因为针对这里item的操作并非独立的，它必须存在于order的环境中。

        return item4return[0];
    }

    /**
     * 组装Item
     *
     * @param smv             skuMoreView
     * @param cart            cart
     * @param paymentClothing paymentClothing
     * @return
     */
    private Item assembleItem(SkuMoreView smv, Cart cart, long paymentClothing) {
        Item item = new Item();
        item.setSkuid(smv.getSkuId());
        item.setSkuMoreId(smv.getSkuMoreId());
        item.setCateCode(smv.getCateCode());
        item.setSprice(smv.getSprice());
        item.setDprice((long) (DiscountRule.calcDprice(smv.getCateCode(), paymentClothing, smv.getSprice(), 0)));
        item.setDcount(cart.getCount());
        item.setPayment(Double.valueOf(cart.getPayment()).longValue());
        item.setState(Item.STATE_OK);
        return item;
    }


}
