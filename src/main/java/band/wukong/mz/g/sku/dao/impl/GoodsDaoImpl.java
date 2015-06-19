package band.wukong.mz.g.sku.dao.impl;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.category.bean.Category;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.dao.GoodsDao;
import band.wukong.mz.g.sku.dao.GoodsDaoValidator;
import band.wukong.mz.nutz.NutzDaoHelper;
import band.wukong.mz.nutz.NutzSqlHelper;
import org.nutz.dao.*;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "goodsDao")
public class GoodsDaoImpl implements GoodsDao {
    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    @Override
    public Goods insert(Goods g) {
        if (!GoodsDaoValidator.insert(g)) {
            throw new IllegalParameterException();
        }
        g.setCtime(new Date());
        return dao.insert(g);
    }

    @Override
    public Goods find(Long id) {
        if (!GoodsDaoValidator.find(id)) {
            throw new IllegalParameterException();
        }
        return dao.fetch(Goods.class, id);
    }

    @Override
    public void update(Goods g) {
        if (!GoodsDaoValidator.update(g)) {
            throw new IllegalParameterException();
        }
        g.setUtime(new Date());
        dao.update(g);
    }

    @Override
    public void rm(Long id) {
        if (!GoodsDaoValidator.rm(id)) {
            throw new IllegalParameterException();
        }
        Goods g = dao.fetch(Goods.class, id);
        g.setState(Goods.STATE_RM);
        dao.update(g);
    }

    @Override
    public QueryResult list(String cateCode, String qcond, int pageNum, int pageSize) {
        SqlExpressionGroup e1 = Cnd.exps("state", "=", Goods.STATE_OK);
        SqlExpressionGroup e2 = Cnd.exps("gname", "like", "%" + qcond + "%");
        Condition c = null;
        if (Strings.isNotBlank(cateCode) && !Category.CATE_CODE_TYPE_SIMPLE.equals(cateCode)) {
            c = Cnd.where(e1).and(e2).and(Cnd.exps("cateCode", "=", cateCode)).desc("ctime");
        } else {
            c = Cnd.where(e1).and(e2).desc("ctime");
        }

        int recordCount = dao.count(Goods.class, c);
        Pager pager = NutzDaoHelper.createPager(pageNum, pageSize, recordCount);
        List<Goods> glist = dao.query(Goods.class, c, pager);

        return new QueryResult(glist, pager);
        //CASE nutz:dao 分页查询的例子
        //CASE nutz:dao SqlExpressionGroup的例子
    }

    @Override
    public QueryResult listWitchHasNoneSku(String qcond, int pageNum, int pageSize) {

        String exp = "select * from t_goods where state = @state";
        if (Strings.isNotBlank(qcond)) {
            exp = exp + " and gname like @gname";
        }
        exp = exp + " and id not in (select goods_id from t_sku) order by ctime desc";

        Sql sql = Sqls.queryEntity(exp);
        sql.params().set("state", Goods.STATE_OK);
        if (Strings.isNotBlank(qcond)) {
            sql.params().set("gname", "%" + qcond + "%");
        }

        int count = count4ListNoneSku(exp, qcond);
        Pager pager = NutzDaoHelper.createPager(pageNum, pageSize, count);
        sql.setPager(pager);

        sql.setEntity(dao.getEntity(Goods.class));
        dao.execute(sql);
        List<Goods> gList = sql.getList(Goods.class);

        return new QueryResult(gList, pager);
    }

    private int count4ListNoneSku(String exp, String qcond) {
        Sql sql = Sqls.create(NutzSqlHelper.convertSql2Count(exp));
        sql.params().set("state", Goods.STATE_OK);
        if (Strings.isNotBlank(qcond)) {
            sql.params().set("gname", "%" + qcond + "%");
        }

        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                int count = rs.next() ? rs.getInt("count(*)") : 0;
                log.debug("count = " + count);
                return count;
            }
        });
        dao.execute(sql);

        return sql.getInt();
    }
}
