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
     * @param o order
     */
    Order insertWithItems(Order o);

    /**
     * find order by id
     *
     * @param id order id
     */
    Order find(long id);

    /**
     * find order within items by id
     *
     * @param id order id
     */
    Order findWithLinks(long id);

    /**
     * list
     *
     * @param qcondOnCust 可以是customer.cid/customer.name/customer.msisdn
     * @param u           当前用户
     * @param p           period of order
     * @param pageNum     pageNum
     * @param pageSize    pageSize
     * @return
     */
    QueryResult list(String qcondOnCust, Period p, User u, int pageNum, int pageSize);

}
