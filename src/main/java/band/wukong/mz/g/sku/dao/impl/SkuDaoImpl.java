package band.wukong.mz.g.sku.dao.impl;

import band.wukong.mz.base.BaseValidator;
import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.bean.Sku;
import band.wukong.mz.g.sku.bean.SkuMore;
import band.wukong.mz.g.sku.dao.SkuDao;
import band.wukong.mz.g.sku.dao.SkuDaoValidator;
import band.wukong.mz.nutz.NutzDaoHelper;
import band.wukong.mz.nutz.NutzSqlHelper;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "skuDao")
public class SkuDaoImpl implements SkuDao {
    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    @Override
    public Sku insertWithMore(Sku s) {
        if (!SkuDaoValidator.insert(s)) {
            throw new IllegalParameterException();
        }

        return dao.insertWith(s, "moreList");
    }

    @Override
    public Sku findWithLinks(Long id) {
        if (!BaseValidator.gtZero(id)) {
            throw new IllegalParameterException();
        }
        Sku sc = dao.fetch(Sku.class, id);
        dao.fetchLinks(sc, "moreList");
        dao.fetchLinks(sc, "goods");
        return sc;
    }

    @Override
    public Sku findWithLinks(String sid) {
        if (Strings.isBlank(sid)) {
            throw new IllegalParameterException();
        }
        Sku sc = dao.fetch(Sku.class, sid);
        dao.fetchLinks(sc, "moreList");
        dao.fetchLinks(sc, "goods");
        return sc;
    }

    @Override
    public void updateWithMore(final Sku s) {
        if (!SkuDaoValidator.udpate(s)) {
            throw new IllegalParameterException();
        }

        s.setUtime(new Date());
        Trans.exec(new Atom() {
            public void run() {
                dao.update(s);
                dao.clear(SkuMore.class, Cnd.where("skuId", "=", s.getId()));
                dao.fastInsert(s.getMoreList());
            }
        });
        //CASE nutz:dao 数据库事务的使用
        // FIXME 这里的事务还没有做失败时的单元测试
    }

    @Override
    public void rm(Long id) {
        if (!BaseValidator.gtZero(id)) {
            throw new IllegalParameterException();
        }
        Sku gs = dao.fetch(Sku.class, id);
        gs.setState(Sku.STATE_RM);
        dao.update(gs);
    }

    @Override
    public QueryResult list(String cateCode, String qcond, int pageNum, int pageSize) {
        if (Strings.isBlank(cateCode)) {
            throw new IllegalParameterException();
        }

        String exp =
                "select t1.id as 'g.id', t1.cate_code as 'g.cate_code', t1.gname as 'g.gname'\n" +
                        "     , t.id as 'sc.id', t.sid as 'sc.sid', t.model as 'sc.model', t.img as 'sc.img', t.pprice as 'sc.pprice', t1.id as 'sc.goods_id'\n" +
                        "from t_sku t\n" +
                        "inner join t_goods t1 on t1.id = t.goods_id\n" +
                        "where t.state = @t_state and t1.state = @t1_state\n" +
                        "and t1.cate_code like @cate_code\n" +
                        "and (t.sid like @qcond or t1.gname like @qcond)";

        Sql sql = Sqls.queryRecord(exp);
        sql.params().set("t_state", Sku.STATE_ON);
        sql.params().set("t1_state", Goods.STATE_OK);
        sql.params().set("cate_code", cateCode + "%");
        sql.params().set("qcond", "%" + qcond + "%");

        int count = count4List(exp, cateCode, qcond);
        Pager pager = NutzDaoHelper.createPager(pageNum, pageSize, count);
        sql.setPager(pager);


        dao.execute(sql);
        List<Record> list = sql.getList(Record.class);
        List<Sku> scList = new ArrayList<Sku>();
        for (Record re : list) {
            Sku sc = re.toEntity(dao.getEntity(Sku.class), "sc.");
            Goods g = re.toEntity(dao.getEntity(Goods.class), "g.");
            sc.setGoods(g);
            scList.add(sc);
        }

        return new QueryResult(scList, pager);
        //CASE nutz:dao 自定义sql 多表查询自动封装多个bean的例子
        /*
        //方法一
        Entity enA = dao.get(XXX.class);
        Entity enB = dao.get(BBB.class);
        //方法二
        sql.setCallback() {
            while (rs.next()){
                A a = enA.getObject(rs, null, "a.");
                B b = enB.getObject(rs, null, "b.");
            }
        }
        */
    }

    /**
     * @param e        原始查询语句，select具体内容的
     * @param cateCode cateCode
     * @param qcond    qcond
     * @return count(*)的数量
     */
    private int count4List(String e, String cateCode, String qcond) {
        Sql sql = Sqls.create(NutzSqlHelper.convertSql2Count(e));
        sql.params().set("t_state", Sku.STATE_ON);
        sql.params().set("t1_state", Goods.STATE_OK);
        sql.params().set("cate_code", cateCode + "%");
        sql.params().set("qcond", "%" + qcond + "%");


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
