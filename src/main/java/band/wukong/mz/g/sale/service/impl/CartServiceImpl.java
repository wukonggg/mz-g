package band.wukong.mz.g.sale.service.impl;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.common.privilege.bean.User;
import band.wukong.mz.g.customer.bean.Customer;
import band.wukong.mz.g.sale.bean.Cart;
import band.wukong.mz.g.sale.service.CartService;
import band.wukong.mz.g.sale.service.CartServiceValidator;
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
    public Cart find(long id) {
        return dao.fetch(Cart.class, id);
    }

    public Cart findByCondition(Cart c) {
        if(c.getUserId() <= 0 || c.getCustId() <= 0 || c.getSkuMoreId() <= 0) {
            throw new IllegalArgumentException();
        }

        return dao.fetch(Cart.class, Cnd.where("userId", "=", c.getUserId())
                .and("custId", "=", c.getCustId())
                .and("skuMoreId", "=", c.getSkuMoreId()));
    }

    public List<Cart> add2Cart(long userId, String skuMoreIds, String cid) {
        List<Cart> carts = new ArrayList<>();

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

        for (String skuMoreId : skuMoreIds.split(",")) {
            Cart c = new Cart();
            c.setUserId(userId);
            c.setCustId(cust.getId());
            c.setSkuMoreId(Long.parseLong(skuMoreId));
            Cart cart = findByCondition(c);
            if (null == cart) {
                carts.add(dao.insert(c));
            } else {
                cart.setCount(cart.getCount() + 1);
                dao.update(cart);
                carts.add(cart);
            }
        }

        return carts;
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
    public void updateCount(long id, int newCount) {
        if (id <= 0 || newCount <= 0) {
            throw new IllegalParameterException();
        }

        Cart cart = dao.fetch(Cart.class, id);
        cart.setCount(newCount);
        dao.update(cart);
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
