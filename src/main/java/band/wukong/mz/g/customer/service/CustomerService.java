package band.wukong.mz.g.customer.service;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.customer.bean.Customer;
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
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "custService")
public class CustomerService {
    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    /**
     * save
     *
     * @param c
     * @return
     */
    public Customer save(Customer c) {
        if (!CustomerServiceValidator.save(c)) {
            throw new IllegalParameterException();
        }
        c.setState(Customer.STATE_OK);
        return dao.insert(c);
    }

    /**
     * find
     *
     * @param id
     * @return
     */
    public Customer find(long id) {
        if (id <= 0) {
            throw new IllegalParameterException();
        }
        return dao.fetch(Customer.class, id);
    }

    /**
     * udpate
     *
     * @param c
     */
    public void update(Customer c) {
        if (!CustomerServiceValidator.update(c)) {
            throw new IllegalParameterException();
        }

        Customer cust = dao.fetch(Customer.class, c.getId());
        c.setPaymentClothing(cust.getPaymentClothing());
        c.setState(Customer.STATE_OK);//修改的时候是不改state的，肯定是ok
        dao.update(c);
    }

    /**
     * rm
     *
     * @param id
     */
    public void rm(long id) {
        if (id <= 0) {
            throw new IllegalParameterException();
        }

        Customer cust = find(id);
        if (null == cust) {
            throw new IllegalParameterException();
        }

        cust.setState(Customer.getStateRm());
        dao.update(cust);
    }

    /**
     * list by page
     *
     * @param qcond    会员卡或姓名或手机号
     * @param pageNum
     * @param pageSize
     * @return
     */
    public QueryResult list(String qcond, int pageNum, int pageSize) {
        Condition condition = null;

        SqlExpressionGroup e1 = Cnd.exps("state", "=", Customer.STATE_OK);
        if (Strings.isNotBlank(qcond)) {
            SqlExpressionGroup e2 = Cnd.exps("cid", "=", qcond).or("name", "=", qcond).or("msisdn", "=", qcond);
            condition = Cnd.where(e1).and(e2);
        } else {
            condition = Cnd.where(e1);
        }

        int recordCount = dao.count(Customer.class, condition);
        Pager pager = NutzDaoHelper.createPager(pageNum, pageSize, recordCount);
        List<Customer> clist = dao.query(Customer.class, condition, pager);

        return new QueryResult(clist, pager);
    }

    /**
     * 自动补全用户名
     *
     * @param keyword
     * @return
     */
    public String autoComplete(String keyword) {
        if (Strings.isBlank(keyword)) {
            return "";
        }

        Condition c = Cnd
                .where("name", "like", keyword + "%")
                .or("msisdn", "like", keyword + "%")
                .or("cid", "like", keyword + "%");
        List<Customer> custList = dao.query(Customer.class, c);

        StringBuilder custs = new StringBuilder();
        for (Customer cust : custList) {
            custs.append(", \"" + cust.getCid() + "/" + cust.getName() + "/" + cust.getMsisdn() + "\"");
        }

        return "[\n" + custs.toString().substring(2) + "\n]";
    }

    /**
     * udpate payment 在order的支付（pay）和退货（return）时用
     *
     * @param c
     */
    public void updatePayment(Customer c) {
        if (!CustomerServiceValidator.updatePayment(c)) {
            throw new IllegalParameterException();
        }
        dao.update(c);
    }
}

