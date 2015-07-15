package band.wukong.mz.g.sale.servcie.impl;

import band.wukong.mz.g.customer.bean.Customer;
import band.wukong.mz.g.sale.bean.Cart;
import band.wukong.mz.g.sale.service.CartService;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class CartServiceImplTest {

    private Ioc ioc;
    private CartService service;
    private static final long USER_TEST_ID = 3;

    @Before
    public void setUp() {
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(CartService.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void clear_add2Cart_listGroupByCust() {
        service.clear(USER_TEST_ID, Customer.NON_MEMBER_ID);

        String skuMoreIds = "198,199,200";
        List<Cart> cartList = service.add2Cart(USER_TEST_ID, skuMoreIds, Customer.NON_MEMBER_CID);
        Map<String, List<Cart>> cartMap = service.listGroupByCust(USER_TEST_ID);
        Assert.assertTrue(cartList.size() == 3);
        Assert.assertTrue(cartMap.size() == 1);
        Assert.assertNotNull(cartMap.get(Customer.NON_MEMBER_CID));
        Assert.assertTrue(cartMap.get(Customer.NON_MEMBER_CID).size() == 3);
        for (Cart c : cartMap.get(Customer.NON_MEMBER_CID)) {
            Assert.assertTrue(skuMoreIds.contains(String.valueOf(c.getSkuMoreId())));
        }
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
    public void list() {
        Map<String, List<Cart>> cartMap =  service.listGroupByCust(999999);
        System.out.println("null != cartMap = " + Boolean.valueOf(null != cartMap));
        System.out.println("cartMap.size() = " + cartMap.size());
    }

    @Test
    public void rm() {
        service.rm(USER_TEST_ID, Customer.NON_MEMBER_ID, 200);
        Map<String, List<Cart>> cartMap =  service.listGroupByCust(USER_TEST_ID);
        Assert.assertTrue(cartMap.size() > 0);
    }

}
