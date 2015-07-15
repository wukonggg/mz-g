package band.wukong.mz.g.sale.service.impl;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.customer.bean.Customer;
import band.wukong.mz.g.privilege.bean.User;
import band.wukong.mz.g.sale.bean.Cart;
import band.wukong.mz.g.sale.service.CartService;
import band.wukong.mz.g.sale.service.CartServiceValidator;
import com.alibaba.fastjson.JSON;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "cartService")
public class CartServiceImpl implements CartService {

    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    @Override
    public List<Cart> add2Cart(long userId, String skuMoreIds, String cid) {
        User user = dao.fetch(User.class, userId);
        if (null == user) {
            SecurityException se = new SecurityException("There is someone try to do something bad...");
            log.error(se.getMessage());
            throw se;
        }

        Customer cust = null;
        if (Strings.isBlank(cid)) {
            cust = dao.fetch(Customer.class, Cnd.where("name", "=", Customer.NON_MEMBER_NAME));
        } else {
            cust = dao.fetch(Customer.class, cid);
        }
        if (null == cust) {
            SecurityException se = new SecurityException("There is someone try to do something bad...");
            log.error(se.getMessage());
            throw se;
        }

        String template = ",{userId:#userId, custId:#custId, skuMoreId: #skuMoreId, count:1}";
        String json = "";
        for (String skuMoreId : skuMoreIds.split(",")) {
            String lin = template
                    .replace("#userId", String.valueOf(userId))
                    .replace("#custId", String.valueOf(cust.getId()))
                    .replace("#skuMoreId", String.valueOf(skuMoreId));
            json = json + lin;
        }
        json = "[" + json.substring(1) + "]";
        List<Cart> carts = JSON.parseArray(json, Cart.class);

        return dao.fastInsert(carts);
    }

    @Override
    public Map<String, List<Cart>> listGroupByCust(long userId) {
        //由于每个营业员在购物车里不可能同时放很多，所以就不group by了。直接查
        List<Cart> cartList = dao.query(Cart.class, Cnd.where("userId", "=", userId));
        Map<String, List<Cart>> cartMap = new HashMap<String, List<Cart>>();
        for (Cart c : cartList) {
            String cid = c.getCid();
            List<Cart> carts = cartMap.get(cid);
            if (null == carts) {
                List<Cart> list = new ArrayList<Cart>();
                list.add(c);
                cartMap.put(cid, list);
            } else {
                carts.add(c);
            }
        }
        return cartMap;
    }

    @Override
    public void rm(long userId, long custId, long skuMoreId) {
        if (!CartServiceValidator.rm(userId, custId, skuMoreId)) {
            throw new IllegalParameterException();
        }
        dao.clear(Cart.class, Cnd.where("custId", "=", custId).and("skuMoreId", "=", skuMoreId).and("userId", "=", userId));
    }

    @Override
    public void clear(long userId, long custId) {
        if (userId <= 0) {
            throw new IllegalParameterException();
        }

        Condition cond = null;
        if (custId > 0) {
            cond = Cnd.where("custId", "=", custId).and("userId", "=", userId);
        } else {
            cond = Cnd.where("userId", "=", userId);
        }

        dao.clear(Cart.class, cond);
    }
}
