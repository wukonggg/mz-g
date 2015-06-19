package band.wukong.mz.g.sku.service;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.sku.bean.SkuMoreView;
import band.wukong.mz.nutz.NutzDaoHelper;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "skuMoreViewService")
public class SkuMoreViewService {
    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    /**
     * @param sids
     * @param pageNum
     * @param pageSize
     * @return
     */
    public QueryResult list(String sids, int pageNum, int pageSize) {

        String s = "";
        if (Strings.isNotBlank(sids)) {
            for (String sid : sids.split("\\s+")) {
                s = s + "or sid like '%" + sid + "%'";
            }
        }
        s = "".equals(s) ? "where 1=1" : s.substring(2);

        Condition c = Cnd.wrap(s);

        int recordCount = dao.count(SkuMoreView.class, c);
        Pager pager = NutzDaoHelper.createPager(pageNum, pageSize, recordCount);
        List<SkuMoreView> skuMoreViewList = dao.query(SkuMoreView.class, c, pager);

        return new QueryResult(skuMoreViewList, pager);
        //CASE nutz:dao 分页查询的例子
        //CASE nutz:dao SqlExpressionGroup的例子
    }

    /**
     * find
     *
     * @param skuMoreId
     * @return
     */
    public SkuMoreView find(long skuMoreId) {
        if (skuMoreId <= 0) {
            throw new IllegalParameterException();
        }
        return dao.fetch(SkuMoreView.class, skuMoreId);
    }
}
