package band.wukong.mz.g.customer.service;

import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.customer.bean.Customer;
import band.wukong.mz.nutz.NutzDaoHelper;
import org.nutz.dao.*;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.util.Daos;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.Date;
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
     * save. save时state会被设为Customer.STATE_OK
     *
     * @param c c
     * @return save的customer
     */
    public Customer save(Customer c) {
        if (!CustomerServiceValidator.save(c)) {
            throw new IllegalParameterException();
        }
        Date now = new Date();
        c.setCtime(now);
        c.setUtime(now);
        c.setState(Customer.STATE_OK);
        return dao.insert(c);
    }

    public Customer find(long id) {
        if (id <= 0) {
            throw new IllegalParameterException();
        }
        return dao.fetch(Customer.class, id);
    }

    public Customer find(String cid) {
        if (Strings.isBlank(cid)) {
            throw new IllegalParameterException();
        }
        return dao.fetch(Customer.class, cid);
    }


    /**
     * find.根据状态
     *
     * @param id    id
     * @param state 状态
     * @return
     */
    public Customer findByState(long id, String state) {
        if (id <= 0 || Strings.isBlank(state)) {
            throw new IllegalParameterException();
        }
        return dao.fetch(Customer.class, Cnd.where("id", "=", id).and("state", "=", state));
    }

    /**
     * find.根据状态
     *
     * @param cid   会员号
     * @param state 状态
     * @return
     */
    public Customer findByState(String cid, String state) {
        if (Strings.isBlank(cid) || Strings.isBlank(state)) {
            throw new IllegalParameterException();
        }
        return dao.fetch(Customer.class, Cnd.where("cid", "=", cid).and("state", "=", state));
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
        Customer cust = find(c.getId());
        if (null == cust) {
            throw new AppRuntimeException("没有找到该用户:" + c.toString());
        }
        c.setUtime(new Date());
        Daos.ext(dao, FieldFilter.create(Customer.class, null, "^ctime|state$", true)).update(c);
    }

    /**
     * rm.设置状态为已删除。当没有查到任何数据时，会记录warn日志，并直接返回,。
     *
     * @param id id
     */
    public void rm(long id) {
        if (id <= 0) {
            throw new IllegalParameterException();
        }

        Customer cust = find(id);
        if (null == cust) {
            log.warn("Could not rm customer whose id = " + id + ", cause could not find him.");
            return;
        }

        cust.setState(Customer.STATE_RM);
        dao.update(cust);
    }

    /**
     * rm.设置状态为已删除。当没有查到任何数据时，会记录warn日志，并直接返回,。
     *
     * @param cid   cid
     */
    public void rm(String cid) {
        if (Strings.isBlank(cid)) {
            throw new IllegalParameterException();
        }

        Customer cust = find(cid);
        if (null == cust) {
            log.warn("Could not rm customer whose cid = " + cid + ", cause could not find him.");
            return;
        }

        cust.setState(Customer.STATE_RM);
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
            condition = Cnd.where(e1).and(e2).orderBy("cid", "asc");
        } else {
            condition = Cnd.where(e1).orderBy("cid", "asc");
        }

        int recordCount = dao.count(Customer.class, condition);
        Pager pager = NutzDaoHelper.createPager(pageNum, pageSize, recordCount);
        List<Customer> clist = dao.query(Customer.class, condition, pager);

        return new QueryResult(clist, pager);
    }

    /**
     * 自动补全用户名
     *
     * @param keyword keyword
     * @return
     */
    public String autoComplete(String keyword) {
        if (Strings.isBlank(keyword)) {
            return "";
        }

        Condition c = Cnd
                .where("name", "like", "%" + keyword + "%")
                .or("msisdn", "like", "%" + keyword + "%")
                .or("cid", "like", keyword + "%");
        List<Customer> custList = dao.query(Customer.class, c);


        StringBuilder custs = new StringBuilder();
        if (null == custList || custList.size() == 0) {
            Customer cust = dao.fetch(Customer.class, 1);
            custs.append(", \"" + cust.getCid() + "/" + cust.getName() + "/" + cust.getMsisdn() + "\"");
        } else {
            for (Customer cust : custList) {
                custs.append(", \"" + cust.getCid() + "/" + cust.getName() + "/" + cust.getMsisdn() + "\"");
            }
        }

        return "[\n" + custs.toString().substring(2) + "\n]";
    }

}

