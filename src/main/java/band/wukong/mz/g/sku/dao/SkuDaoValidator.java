package band.wukong.mz.g.sku.dao;

import band.wukong.mz.base.BaseValidator;
import band.wukong.mz.g.sku.bean.Sku;
import band.wukong.mz.g.sku.bean.SkuMore;
import org.nutz.lang.Strings;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class SkuDaoValidator {

    /**
     * validate before save.
     *
     * @param sc
     */
    public static boolean insert(Sku sc) {
        boolean ready = sc.getId() <= 0
                && Strings.isNotBlank(sc.getSid())
                && Strings.isNotBlank(sc.getModel())
                && Strings.isNotBlank(sc.getType())
                && BaseValidator.notNull(sc.getPtime())
                && BaseValidator.gtZero(sc.getPprice())
                && BaseValidator.gtZero(sc.getSprice())
                && BaseValidator.notNull(sc.getCtime())
                && null != sc.getUtime()
                && Strings.isNotBlank(sc.getState())
                && sc.getGoodsId() > 0
                && null != sc.getMoreList() && sc.getMoreList().size() > 0;

        if (!ready) {
            return false;
        } else {
            for (SkuMore scm : sc.getMoreList()) {
                if (scm.getSkuId() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * validate before update.
     *
     * @param sc
     */
    public static boolean udpate(Sku sc) {
        boolean ready = sc.getId() > 0
                && Strings.isNotBlank(sc.getSid())
                && Strings.isNotBlank(sc.getModel())
                && Strings.isNotBlank(sc.getType())
                && BaseValidator.gtZero(sc.getPprice())
                && BaseValidator.notNull(sc.getPtime())
                && BaseValidator.notNull(sc.getCtime())
                && BaseValidator.notNull(sc.getUtime())
                && Strings.isNotBlank(sc.getState())
                && sc.getGoodsId() > 0
                && null != sc.getMoreList() && sc.getMoreList().size() > 0;

        if (!ready) {
            return false;
        } else {
            for (SkuMore scm : sc.getMoreList()) {
                if (scm.getSkuId() < 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
