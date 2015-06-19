package band.wukong.mz.g.sale.dao;

import band.wukong.mz.base.bean.Period;
import band.wukong.mz.g.privilege.bean.User;
import band.wukong.mz.g.sale.bean.Order;
import org.nutz.dao.QueryResult;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public interface OrderDao {
    /**
     * save order within items
     *
     * @param o
     */
    void insertWithItems(Order o);

    /**
     * find order within items by id
     *
     * @param id
     */
    Order find(long id);

    /**
     * list
     *
     * @param pageNum     pageNum
     * @param pageSize    pageSize
     * @param p           period of order
     * @param qcondOnCust 可以是customer.cid/customer.name/customer.msisdn
     * @param u           当前用户
     * @return
     */
    QueryResult list(int pageNum, int pageSize, Period p, String qcondOnCust, User u);

}
