package band.wukong.mz.g.customer.service;

import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class CustomerServiceTest {
    private Ioc ioc;
    private CustomerService service;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(CustomerService.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void autoComplete() {
        String json = service.autoComplete("18");
        Assert.assertNotNull(json);
        System.out.println("===========================================");
        System.out.println(json);
        System.out.println("===========================================");
    }
}
