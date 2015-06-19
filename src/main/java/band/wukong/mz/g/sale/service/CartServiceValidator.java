package band.wukong.mz.g.sale.service;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class CartServiceValidator {

    public static boolean rm(long userId, long custId, long skuMoreId) {
        return userId > 0 && custId > 0 && skuMoreId > 0;
    }

}
