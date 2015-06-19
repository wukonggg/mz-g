package band.wukong.mz.g.sale.service;

import band.wukong.mz.g.sale.bean.Cart;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class OrderServiceValidator {

    public static boolean pay(Cart[] carts, Long userId) {
        if (null == carts || carts.length == 0 || userId <= 0) {
            return false;
        }

        for (Cart c : carts) {
            if (c.getCustId() <= 0 || c.getSkuMoreId() <= 0 || c.getCount() <= 0) {
                return false;
            }
        }

        return true;
    }
}
