package band.wukong.mz.g.sale.service;

import band.wukong.mz.g.sale.bean.Cart;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public interface CartService {

    /**
     * 加入购物车
     *
     * @param userId     userId
     * @param skuMoreIds skuMoreIds. sample: 3,5,66,8
     * @param cid        cid
     * @return 添加到购物车中的商品
     */
    List<Cart> add2Cart(long userId, String skuMoreIds, String cid);

    /**
     * 根据客户分组查询cart。
     *
     * @param userId userId
     * @return Map的key是cid
     */
    Map<String, List<Cart>> listGroupByCust(long userId);

    /**
     * 从购物车中删除商品
     *
     * @param userId    用户id
     * @param custId    custId
     * @param skuMoreId skuMoreId
     */
    void rm(long userId, long custId, long skuMoreId);

    /**
     * 清空购物车
     *
     * @param userId 用户id
     * @param custId custId
     */
    void clear(long userId, long custId);
}
