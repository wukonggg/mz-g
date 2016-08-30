package band.wukong.mz.g.sale.service;

import band.wukong.mz.base.bean.Period;
import band.wukong.mz.common.privilege.bean.User;
import band.wukong.mz.g.sale.bean.Cart;
import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.bean.Order;
import org.nutz.dao.QueryResult;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public interface OrderService {

    /**
     * find order by id
     *
     * @param id
     */
    Order find(long id);

    /**
     * 查询带有详细信息的order list。包括用户、购买的产品。
     *
     * @param qcondOnCust 可以是customer.cid/customer.name/customer.msisdn
     * @param p           period of order
     * @param u           当前用户
     * @param pageNum     pageNum
     * @param pageSize    pageSize
     * @return
     */
    QueryResult listDetail(String qcondOnCust, Period p, User u, int pageNum, int pageSize);

    /**
     * 支付/结帐。库存不足时抛出OutOfStockException，整个订购回滚
     * 1、查询用户是否存在、营业员是否存在。都存在才下一步
     * 2、查询产品的库存是否都足够。足够才下一步
     * 3、下单
     * 4、更新库存
     * 5、看cust是不是非会员顾客、下单内容中有无服装类。都满足就更新用户表paymentclothing的值，新值为原有值+新单中服装类商品的成交价
     * 6、删除购物车中已下单的产品
     * 7、判断goods所有的sku的count是否为0，为0自动下架sku，不为0不做操作。
     *
     * @param carts  carts
     * @param userId userId
     * @return order insert的order，包括items
     */
    Order pay(Cart[] carts, Long userId);

    /**
     * 退货
     * 1、查出要return的item，更新状态为'退货'，同时保存退货的时间和说明
     * 2、更新状态为'退货'，同时保存退货的时间和说明
     * 3、恢复库存
     * 4、如果商品是服装类，就更新用户表服装paymentClothing的值，新值为原有值+新单中服装类商品的成交价（成交价为负值）
     * 4.1、如果是非会员客户就不用算paymentClothing了
     *
     * @param item   使用id, state, dcount, returnUserId, returnReason, returnDesc
     * @return return的item
     */
    Item returnItem(Item item);

    /**
     * 查询item，包括sku4item及其所在的order
     *
     * @param itemId
     */
    Item findItemWithOrder(long itemId);

}
