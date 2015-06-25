package band.wukong.mz.g.sale.servcie.impl;

import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.service.impl.OrderServiceImpl;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class OrderServiceImplTest {

    private Ioc ioc;
    private OrderServiceImpl service;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(OrderServiceImpl.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }


    @Test
    public void returnItem() {
        Item i = new Item();
        i.setId(9);
        i.setReturnUserId(1);
        i.setDcount(1);
        i.setReturnReason(Item.RETURN_REASON_SIZE);
        i.setReturnDesc("desc");

        service.returnItem(i);
    }
}
