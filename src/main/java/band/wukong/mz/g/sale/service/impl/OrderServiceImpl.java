package band.wukong.mz.g.sale.service.impl;

import band.wukong.mz.base.bean.Period;
import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.common.privilege.bean.User;
import band.wukong.mz.common.privilege.service.UserService;
import band.wukong.mz.g.customer.bean.Customer;
import band.wukong.mz.g.customer.service.CustomerService;
import band.wukong.mz.g.sale.bean.Cart;
import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.bean.Order;
import band.wukong.mz.g.sale.dao.ItemDao;
import band.wukong.mz.g.sale.dao.OrderDao;
import band.wukong.mz.g.sale.exception.OutOfStockException;
import band.wukong.mz.g.sale.service.CartService;
import band.wukong.mz.g.sale.service.OrderService;
import band.wukong.mz.g.sale.service.OrderServiceValidator;
import band.wukong.mz.g.sku.bean.SkuMoreView;
import band.wukong.mz.g.sku.dao.SkuDao;
import band.wukong.mz.g.sku.service.SkuMoreViewService;
import band.wukong.mz.g.sku.service.SkuService;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.ArrayList;
import java.util.Date;
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
    private SkuDao skuDao;

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
    public Order pay(final Cart[] carts, final Long userId) {
        if (!OrderServiceValidator.pay(carts, userId)) {
            throw new IllegalParameterException();
        }

        final Order[] order = new Order[1];    //order for return

        //1、查询用户是否存在、营业员是否存在。都存在才下一步
        if (null == userService.fetch(userId)) {
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
                // 2、查询产品的库存是否都足够, 如库存不足时抛出OutOfStockException
                // 3、创建订单
                // 4、更新库存
                // 5、判断goods所有的sku的count是否为0，为0自动下架sku，不为0不做操作。
                Order o = new Order();
                o.setUserId(userId);
                o.setCustId(carts[0].getCustId());
                o.setDtime(new Date());
                o.setItems(new ArrayList<Item>());  // 订单中的item
                for (Cart c : carts) {
                    //查出对应的skuMore，库存不够就抛出异常
                    SkuMoreView smv = smvService.find(c.getSkuMoreId());
                    if (smv.getCount() < c.getCount()) {
                        throw new OutOfStockException("库存不够");
                    }

                    Item item = assembleItem(smv, c);
                    o.getItems().add(item);

                    //更新库存（减少）
                    skuService.reduceStock(smv.getSkuMoreId(), c.getCount());

                    // 5、判断goods所有的sku的count是否为0，为0自动下架sku，不为0不做操作。
                    boolean isCount0 = skuDao.countByGoodsId_STATE_NOT_RM(smv.getGoodsId()) == 0;
                    if (isCount0) {
                        skuService.offShelf(smv.getGoodsId());
                    }
                }

                order[0] = orderDao.insertWithItems(o);    //创建订单

                //6、删除购物车中已下单的产品
                for (Cart c : carts) {
                    cartService.rm(userId, cust.getId(), c.getSkuMoreId());
                }
            }
        });
        return order[0];
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
        // 1、查出要return的item
        Item item = itemDao.find(i.getId());
        item.setState(Item.STATE_RETURN);
        item.setReturnReason(i.getReturnReason());
        item.setReturnDesc(i.getReturnDesc());

        Trans.exec(new Atom() {
            public void run() {
                // 2、更新状态为'退货'，同时保存退货的时间和说明
                itemDao.update(item);

                //3、恢复库存
                skuService.addStock(item.getSkuMoreId(), item.getDcount());
            }
        });

        return item;
    }

    /**
     * 组装Item
     *
     * @param smv             skuMoreView
     * @param cart            cart
     * @return
     */
    private Item assembleItem(SkuMoreView smv, Cart cart) {
        Item item = new Item();
        item.setSkuid(smv.getSkuId());
        item.setSkuMoreId(smv.getSkuMoreId());
        item.setCateCode(smv.getCateCode());
        item.setSprice(smv.getSprice());
        item.setDprice(smv.getSprice());
        item.setDcount(cart.getCount());
        item.setPayment(Double.valueOf(cart.getPayment()).longValue());
        item.setState(Item.STATE_OK);
        return item;
    }


}
