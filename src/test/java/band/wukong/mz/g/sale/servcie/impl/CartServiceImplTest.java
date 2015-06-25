package band.wukong.mz.g.sale.servcie.impl;

import band.wukong.mz.g.sale.bean.Cart;
import band.wukong.mz.g.sale.service.impl.CartServiceImpl;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class CartServiceImplTest {

    private Ioc ioc;
    private CartServiceImpl service;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(CartServiceImpl.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test(expected = SecurityException.class)
    public void add2Cart_FailedOnSecurityException() {
        String skuMoreIds = "991,992,993";
        long userId = 999999L;
        String cid = "888";
        service.add2Cart(userId, skuMoreIds, cid);
        //CASE junit 捕捉异常
    }

    @Test
    public void listGroupByCust() {
        Map<String, List<Cart>> cartMap = service.listGroupByCust(1L);
        Set<String> keys = cartMap.keySet();
        for (String key : keys) {
            System.out.println("===========================");
            System.out.println("key = " + key);
            List<Cart> carts = cartMap.get(key);
            for (Cart c : carts) {
                System.out.println("c = " + c);
            }
        }
    }

    @Test
    public void rm() {
        service.rm(111L, 111L, 111L);
    }

    @Test
    public void clear() {
        service.clear(1L, 222L);
    }

}
