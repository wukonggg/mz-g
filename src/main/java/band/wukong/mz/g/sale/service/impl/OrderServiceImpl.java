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
import band.wukong.mz.g.sale.dao.ItemDao;
import band.wukong.mz.g.sale.dao.OrderDao;
import band.wukong.mz.g.sale.service.CartService;
import band.wukong.mz.g.sale.service.DiscountRule;
import band.wukong.mz.g.sale.service.OrderService;
import band.wukong.mz.g.sale.service.OrderServiceValidator;
import band.wukong.mz.g.sku.bean.SkuMoreView;
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
    public QueryResult listDetail(int pageNum, int pageSize, Period p, String qcondOnCust, User u) {
        QueryResult qr = orderDao.list(pageNum, pageSize, p, qcondOnCust, u);
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
                if (o.getCustId() != Customer.ID_NON_MEMBER && newPaymentClothing > nowPaymentClothing) {
                    cust.setPaymentClothing(newPaymentClothing);
                    custService.update(cust);
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
    public void returnItem(final Item i) {
        Trans.exec(new Atom() {
            public void run() {
                //1、查到item
                Item item4buy = itemDao.find(i.getId());

                //2、update item： 原item减去要退货的数量，计算新的交易金额
                int newDcount = item4buy.getDcount() - i.getDcount();
                if (newDcount < 0) {
                    throw new AppRuntimeException("尼玛！退货数量比购买数量还多？");
                }
                item4buy.setDcount(newDcount);
                item4buy.setPayment(item4buy.getDprice() * item4buy.getDcount());
                itemDao.update(item4buy);

                //3、insert item：退货的数量，计算新的交易金额（负数），状态为'退货'，同时保存退货的时间和说明
                HashMap<String, Object> itemMap = Lang.obj2map(item4buy, HashMap.class);
                Item item4return = Lang.map2Object(itemMap, Item.class);
                item4return.setId(0);
                item4return.setDcount(i.getDcount());
                item4return.setPayment(0 - item4return.getDprice() * item4return.getDcount());  //取负数
                item4return.setReturnTime(new Date());
                item4return.setReturnUserId(i.getReturnUserId());
                item4return.setReturnReason(i.getReturnReason());
                item4return.setReturnDesc(i.getReturnDesc());
                item4return.setState(Item.STATE_RETURN);
                itemDao.insert(item4return);

                //4、恢复库存
                skuService.addStock(item4return.getSkuMoreId(), i.getDcount());
            }
        });
        //CASE arch:layer 这里对item的操作，应该放在哪？OrderService里，还是ItemService里？
        //还是orderService中。因为针对这里item的操作并非独立的，它必须存在于order的环境中。
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
