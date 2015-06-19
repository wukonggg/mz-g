package band.wukong.mz.g.sku.service;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.sku.bean.SkuPropType;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
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

    private static final Log log = Logs.get();

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

        String e = "SELECT t.item, t.name, t.title, t.evalue, t.cvalue\n" +
                "FROM t_sku_prop_type t\n" +
                "WHERE t.cate_code=@cateCode\n" +
                "GROUP BY t.name\n" +
                "ORDER BY t.name_order;";

        Sql sql = Sqls.queryEntity(e);
        sql.params().set("cateCode", cateCode);
        sql.setEntity(dao.getEntity(SkuPropType.class));
        dao.execute(sql);
        return sql.getList(SkuPropType.class);
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
        String e = "SELECT t.cate_code, t.item, t.name, t.title, t.evalue, t.cvalue, t.name_order, t.value_order\n" +
                "FROM t_sku_prop_type t\n" +
                "WHERE t.item=@item AND t.name=@name AND t.cate_code=@cateCode\n" +
                "ORDER BY t.name_order;";
        Sql sql = Sqls.queryEntity(e);
        sql.params().set("item", sd.getItem());
        sql.params().set("name", sd.getName());
        sql.params().set("cateCode", sd.getCateCode());
        sql.setEntity(dao.getEntity(SkuPropType.class));

        dao.execute(sql);

        return sql.getList(SkuPropType.class);
    }
}
