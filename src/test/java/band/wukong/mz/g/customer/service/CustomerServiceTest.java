package band.wukong.mz.g.customer.service;

import band.wukong.mz.g.customer.bean.Customer;
import band.wukong.mz.nutz.test.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.Ioc;

/**
 * As you see...
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
    public void save_find() {
        Customer c = new Customer();
        c.setCid("junit_cid");
        Customer cust1 = service.save(c);
        Assert.assertNotNull(cust1);

        Customer cust2 = service.find(cust1.getId());
        Assert.assertNotNull(cust2);
        Assert.assertEquals(cust1, cust2);

    }

    @Test
    public void findByState() {
        Customer c = new Customer();
        c.setCid("junit_cid");

        Customer cust1 = service.findByState(c.getCid(), Customer.STATE_OK);
        Assert.assertNotNull(cust1);

        Customer cust2 = service.findByState(cust1.getId(), Customer.STATE_OK);
        Assert.assertEquals(cust1, cust2);
    }

    @Test
    public void rm_findByState_update_findByState() {
        Customer c = new Customer();
        c.setCid("junit_cid");
        service.rm(c.getCid());

        Customer cust1 = service.findByState(c.getCid(), Customer.STATE_RM);
        Assert.assertEquals(c.getCid(), cust1.getCid());

        cust1.setState(Customer.STATE_OK);
        service.update(cust1);
        Customer cust2 = service.findByState(cust1.getId(), Customer.STATE_RM);
        Customer cust3 = service.findByState(cust1.getId(), Customer.STATE_OK);
        Assert.assertNull(cust2);
        Assert.assertEquals(cust1.getCid(), cust3.getCid());
    }

    @Test
    public void rm_find_rm_find() {
        Customer c = new Customer();
        c.setCid("junit_cid");

        service.rm(c.getCid());
        Customer cust1 = service.find(c.getCid());
        Assert.assertEquals(Customer.STATE_RM, cust1.getState());

        service.rm(cust1.getId());
        Customer cust2 = service.find(cust1.getId());
        Assert.assertEquals(Customer.STATE_RM, cust2.getState());
    }

    @Test
    public void list() {
        QueryResult qr = service.list("", 0, 5);
        Assert.assertNotNull(qr.getPager());
        Assert.assertNotNull(qr.getList());
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
