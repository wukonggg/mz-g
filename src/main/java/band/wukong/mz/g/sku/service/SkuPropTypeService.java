package band.wukong.mz.g.sku.service;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.sku.bean.SkuPropType;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "skuPropTypeService")
public class SkuPropTypeService {

    @Inject
    private Dao dao;

    /**
     * 查询某个item下的distinct name
     *
     * @param cateCode cateCode
     * @return List<SkuDict>
     */
    public List<SkuPropType> listDistinctNameByCateCode(String cateCode) {
        if (Strings.isBlank(cateCode)) {
            throw new IllegalParameterException();
        }

        return dao.query(SkuPropType.class, Cnd.where("cateCode", "=", cateCode)
                .groupBy("name").asc("nameOrder"));
    }


    /**
     * 查询
     *
     * @param sd
     * @return
     */
    public List<SkuPropType> list(SkuPropType sd) {
        if (!SkuDictServiceValidator.list(sd)) {
            throw new IllegalParameterException();
        }

        return dao.query(SkuPropType.class, Cnd.where("cateCode", "=", sd.getCateCode())
                .and("item", "=", sd.getItem())
                .and("name", "=", sd.getName())
                .asc("nameOrder"));
    }
}
