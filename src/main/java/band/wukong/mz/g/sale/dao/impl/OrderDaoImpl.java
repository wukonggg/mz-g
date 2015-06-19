package band.wukong.mz.g.sale.dao.impl;

import band.wukong.mz.base.bean.Period;
import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.customer.bean.Customer;
import band.wukong.mz.g.privilege.bean.User;
import band.wukong.mz.g.sale.bean.Order;
import band.wukong.mz.g.sale.dao.OrderDao;
import band.wukong.mz.g.sale.dao.OrderDaoValidator;
import band.wukong.mz.nutz.NutzDaoHelper;
import band.wukong.mz.nutz.NutzSqlHelper;
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "orderDao")
public class OrderDaoImpl implements OrderDao {

    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    @Override
    public void insertWithItems(Order o) {
        if (!OrderDaoValidator.save(o)) {
            throw new IllegalParameterException();
        }
        dao.insertWith(o, "items");
    }

    @Override
    public Order find(long id) {
        return dao.fetch(Order.class, id);
    }

    @Override
    public QueryResult list(int pageNum, int pageSize, Period p, String qcondOnCust, User u) {
        if (u.getId() <= 0) {
            throw new IllegalParameterException();
        }

        String exp =
                "select o.id as 'o.id', o.cust_id as 'o.custId', o.dtime as 'o.dtime'\n" +
                        "     , c.id as 'c.id', c.cid as 'c.cid', c.name as 'c.name', c.msisdn as 'c.msisdn'\n" +
                        "from t_order o\n" +
                        "inner join t_customer c on c.id = o.cust_id\n" +
                        "where o.user_id = @userId\n";
        if (null != p) {
            if (null != p.getStartDate()) {
                exp = exp + "and o.dtime >= @startDate\n";
            }
            if (null != p.getEndDate()) {
                exp = exp + "and o.dtime <= @endDate \n";
            }
        }
        if (Strings.isNotBlank(qcondOnCust)) {
            exp = exp + "and (c.cid like @cid or c.name = @name or c.msisdn = @msisdn)\n";
        }
        exp = exp + "order by o.dtime desc\n";

        Sql sql = Sqls.queryRecord(exp);
        sql.params().set("userId", u.getId());
        if (null != p) {
            if (null != p.getStartDate()) {
                sql.params().set("startDate", p.getStartDate());
            }
            if (null != p.getEndDate()) {
                sql.params().set("endDate", p.getEndDate());
            }
        }
        if (Strings.isNotBlank(qcondOnCust)) {
            sql.params().set("cid", "%" + qcondOnCust + "%");
            sql.params().set("name", qcondOnCust);
            sql.params().set("msisdn", qcondOnCust);
        }

        int count = count4List(exp, p, qcondOnCust, u.getId());
        Pager pager = NutzDaoHelper.createPager(pageNum, pageSize, count);
        sql.setPager(pager);

        dao.execute(sql);
        List<Record> list = sql.getList(Record.class);
        List<Order> oList = new ArrayList<Order>();
        for (Record re : list) {
            Order order = re.toEntity(dao.getEntity(Order.class), "o.");
            Customer cust = re.toEntity(dao.getEntity(Customer.class), "c.");
            order.setCust(cust);
            oList.add(order);
        }
        return new QueryResult(oList, pager);
    }

    /**
     * count
     *
     * @param exp
     * @param p
     * @param qcondOnCust
     * @return
     */
    private int count4List(String exp, Period p, String qcondOnCust, long userId) {
        Sql sql = Sqls.create(NutzSqlHelper.convertSql2Count(exp));

        sql.params().set("userId", userId);
        if (null != p) {
            if (null != p.getStartDate()) {
                sql.params().set("startDate", p.getStartDate());
            }
            if (null != p.getEndDate()) {
                sql.params().set("endDate", p.getEndDate());
            }
        }
        if (Strings.isNotBlank(qcondOnCust)) {
            sql.params().set("cid", "%" + qcondOnCust + "%");
            sql.params().set("name", qcondOnCust);
            sql.params().set("msisdn", qcondOnCust);
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
