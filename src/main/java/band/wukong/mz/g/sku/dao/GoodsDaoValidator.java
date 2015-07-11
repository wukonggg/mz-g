package band.wukong.mz.g.sku.dao;

import band.wukong.mz.base.BaseValidator;
import band.wukong.mz.g.sku.bean.Goods;
import org.nutz.lang.Strings;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class GoodsDaoValidator {

    /**
     * validate before save.
     *
     * @param g
     */
    public static boolean insert(Goods g) {
        return g.getId() <= 0
                && Strings.isNotBlank(g.getCateCode())
                && Strings.isNotBlank(g.getGname())
                && (Goods.STATE_OK.equals(g.getState()) || Goods.STATE_RM.equals(g.getState()));
    }

    /**
     * validate before update.
     *
     * @param g
     */
    public static boolean update(Goods g) {
        return BaseValidator.gtZero(g.getId())
                && Strings.isNotBlank(g.getCateCode())
                && Strings.isNotBlank(g.getGname())
                && (Goods.STATE_OK.equals(g.getState()) || Goods.STATE_RM.equals(g.getState()));
    }

    /**
     * validate before remove.
     *
     * @param id
     */
    public static boolean rm(Long id) {
        return BaseValidator.gtZero(id);
    }
}
