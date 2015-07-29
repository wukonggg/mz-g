package band.wukong.mz.g.sku.service;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.sku.bean.SkuMoreView;
import band.wukong.mz.nutz.NutzDaoHelper;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.cri.SqlExpressionGroup;
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
     * list
     *
     * @param sids 空格隔开的sid
     * @param pageNum pageNum
     * @param pageSize pageSize
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
     * @param skuMoreId skuMoreId
     * @return
     */
    public SkuMoreView find(long skuMoreId) {
        if (skuMoreId <= 0) {
            throw new IllegalParameterException();
        }
        return dao.fetch(SkuMoreView.class, skuMoreId);
    }

    /**
     * 查询剩余库存量，根据品类和库存阈值
     *
     * @param cateCodes 品类数组，cateCodes.length == counts.length
     * @param counts 库存阈值数组，counts.length == cateCodes.length
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return
     */
    public QueryResult listSkuRest(String[] cateCodes, int[] counts, int pageNum, int pageSize) {
        if (null == cateCodes || null == counts
                || cateCodes.length == 0 || counts.length == 0
                || cateCodes.length != counts.length) {
            throw new IllegalParameterException();
        }

        SqlExpressionGroup[] exps = new SqlExpressionGroup[cateCodes.length];
        for (int i = 0; i < cateCodes.length; i++) {
            exps[i] = Cnd.exps("cateCode", "=", cateCodes[i]).and("count", "<=", counts[i]);
        }
        Cnd cnd = Cnd.where(exps[0]);
        for (int i = 1; i < exps.length; i++) {
            cnd.or(exps[i]);
        }
        Condition condition = cnd.asc("cateCode").asc("count");

        int recordCount = dao.count(SkuMoreView.class, condition);
        Pager pager = NutzDaoHelper.createPager(pageNum, pageSize, recordCount);
        List<SkuMoreView> skuMoreViewList = dao.query(SkuMoreView.class, condition, pager);

        return new QueryResult(skuMoreViewList, pager);
    }
}
