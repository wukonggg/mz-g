package band.wukong.mz.g.customer.module;

import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.g.AppConst;
import band.wukong.mz.g.customer.bean.Customer;
import band.wukong.mz.g.customer.service.CustomerService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean
@At("/customer/cust")
@Filters(@By(type = CheckSession.class, args = {"me", "/entry.io"}))
public class CustomerMoudle {
    private static final Log log = Logs.get();

    @Inject
    private CustomerService custService;

    @At("/list")
    @Ok("jsp:view.customer.cust_list")
    public Object list(@Param("qcond") String qcond, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {
        log.debug("Input params - qcond = " + qcond);
        log.debug("Input params - pageNum = " + pageNum);
        log.debug("Input params - pageSize = " + pageSize);

        if (null == qcond) {
            qcond = "";
        }
        if (pageSize == 0) {
            pageSize = AppConst.PAGE_SIZE;
        }

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("qcond", qcond);
        retMap.put("result", custService.list(qcond, pageNum, pageSize));
        return retMap;
    }

    @At("/add")
    @Ok("jsp:view.customer.cust_add")
    public void add() {
    }

    @At("/save")
    @Ok("redirect:/customer/cust/list.io")
    public void save(@Param("..") Customer c) {
        log.debug("Input params - customer: \n" + c);

        custService.save(c);
    }

    @At("/mod")
    @Ok("jsp:view.customer.cust_mod")
    public Customer mod(@Param("id") Long id) {
        log.debug("Input params - id: \n" + id);

        return custService.find(id);
    }

    @At("/upd")
    @Ok("redirect:/customer/cust/list.io")
    public void upd(@Param("..") Customer c) {
        log.debug("Input params - goods:\n" + c);

        if (c.getId() <= 0) {
            throw new AppRuntimeException("木有找到介个银。。。");
        }

        custService.update(c);
    }

    @At("/rm")
    @Ok("redirect:/customer/cust/list.io")
    public void rm(@Param("id") Long id) {
        log.debug("Input params - id:" + id);

        if (null == id || id <= 0) {
            throw new AppRuntimeException("木有找到介个银。。。");
        }

        custService.rm(id);
    }

    @At("/autocomplete")
    @Ok("raw")
    public String autoComplete(@Param("keyword") String keyword) {
        log.debug("Input params - keyword: \n" + keyword);

        return custService.autoComplete(keyword);
//        return "[\"yolanda\",\"wukong\",\"mz\"]";
    }
}
