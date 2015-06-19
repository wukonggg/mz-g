package band.wukong.mz.g.sku.service;

import band.wukong.mz.g.sku.bean.SkuPropType;
import org.nutz.lang.Strings;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class SkuDictServiceValidator {

    public static boolean list(SkuPropType sd) {
        return Strings.isNotBlank(sd.getItem())
                && Strings.isNotBlank(sd.getName())
                && Strings.isNotBlank(sd.getCateCode());
    }
}
