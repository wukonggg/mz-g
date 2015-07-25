package band.wukong.mz.g.sale.module;

import band.wukong.mz.base.bean.Period;
import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.privilege.bean.User;
import band.wukong.mz.g.sale.bean.Cart;
import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.service.CartService;
import band.wukong.mz.g.sale.service.OrderService;
import band.wukong.mz.g.sku.service.SkuMoreViewService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Order的业务流程简述：
 * 1、根据sid查询sku。可多个查询条件用逗号隔开
 * 2、（可重复本步骤）选择sku，加入购物车
 * -  库存为0的sku可以被查询出来，但是无法加入购物车
 * 3、查看购物车，进行购买数量的调整
 * -  数量为1的sku可以被删除，但是无法将数值调整为小于1的整数
 * 4、选择会员：文本框提示
 * 5、结帐
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean
@At("/sale/order")
@Filters(@By(type = CheckSession.class, args = {"me", "/entry.io"}))
public class OrderModule {

    private static final Log log = Logs.get();

    @Inject("refer:orderService")
    private OrderService oservice;

    @Inject("refer:skuMoreViewService")
    private SkuMoreViewService smvService;

    @Inject("refer:cartService")
    private CartService cartService;


    @At("/list")
    @Ok("jsp:view.sale.order_list")
    public Object list(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize,
                       @Param("::p.") Period p,
                       @Param("qcond_c") String qcondOnCust, HttpSession session) {
        //CASE nutz:mvc 使用前缀式表单
        log.debug("Input params - pageNum: " + pageNum);
        log.debug("Input params - pageSize: " + pageSize);
        log.debug("Input params - period: " + p);

        if (pageSize == 0) {
            pageSize = 2;
        }

        User u = (User) session.getAttribute("me");

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("period", p);
        retMap.put("qcond_c", qcondOnCust);
        retMap.put("result", oservice.listDetail(qcondOnCust, p, u, pageNum, pageSize));
        return retMap;
    }

    @At("/buy")
    @Ok("jsp:view.sale.order_buy")
    public Object buy(@Param("sids") String sids, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {
        log.debug("Input params - sids: " + sids);
        log.debug("Input params - pageNum: " + pageNum);
        log.debug("Input params - pageSize: " + pageSize);

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("sids", sids);
        retMap.put("result", smvService.list(sids, pageNum, pageSize));
        return retMap;
    }

    @At("/cart/add")
    @Ok("raw")
    public String add2Cart(@Param("skuMoreIds") String skuMoreIds, @Param("cid") String cid, HttpSession session) {
        log.debug("Input params - skuMoreIds: " + skuMoreIds);
        log.debug("Input params - cid: " + cid);

        User u = (User) session.getAttribute("me");

        try {
            cartService.add2Cart(u.getId(), skuMoreIds, cid);
        } catch (RuntimeException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
            return "duang...\n" + e.getMessage();
        }
        return "done";
    }

    @At("/cart/list")
    @Ok("jsp:view.sale.order_cart")
    public Object listCart(HttpSession session) {
        User u = (User) session.getAttribute("me");
        return cartService.listGroupByCust(u.getId());
    }

    @At("/cart/rm")
    @Ok("raw")
    public String rmCart(@Param("custId") long custId, @Param("skuMoreId") long skuMoreId, HttpSession session) {
        User u = (User) session.getAttribute("me");
        try {
            cartService.rm(u.getId(), custId, skuMoreId);
        } catch (RuntimeException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
            return "duang...\n" + e.getMessage();
        }
        return "done";
    }

    @At("/cart/clear")
    @Ok("raw")
    public String clearCart(@Param("custId") long custId, HttpSession session) {
        log.debug("Input params - custId: " + custId);

        User u = (User) session.getAttribute("me");
        try {
            cartService.clear(u.getId(), custId);
        } catch (RuntimeException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
            return "duang...\n" + e.getMessage();
        }
        return "done";
    }


    @At("/pay")
    @Ok("redirect:/sale/order/list.io")
    public void pay(@Param("carts") Cart[] carts, HttpSession session) {
        if (null == carts) {
            throw new IllegalParameterException("购物车为空，你想干嘛？");
        }

        User u = (User) session.getAttribute("me");
        oservice.pay(carts, u.getId());
    }

    @At("/return/s1")
    @Ok("jsp:view.sale.order_return")
    public Item return_s1(@Param("id") long itemId) {
        log.debug("Input params - itemId: " + itemId);
        return oservice.findItemWithOrder(itemId);
    }

    @At("/return/s2")
    @Ok("redirect:/sale/order/list.io")
    public void return_s2(@Param("..") Item i, HttpSession session) {
        log.debug("Input params - item: " + i);

        User u = (User) session.getAttribute("me");
        i.setReturnUserId(u.getId());
        oservice.returnItem(i);
    }
}
