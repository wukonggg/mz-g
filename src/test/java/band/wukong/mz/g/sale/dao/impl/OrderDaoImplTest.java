package band.wukong.mz.g.sale.dao.impl;

import band.wukong.mz.base.bean.Period;
import band.wukong.mz.g.privilege.bean.User;
import band.wukong.mz.g.sale.bean.Order;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.Ioc;

import java.util.Calendar;
import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class OrderDaoImplTest {

    private Ioc ioc;
    private OrderDaoImpl dao;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        dao = ioc.get(OrderDaoImpl.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }



    @Test
    public void list() {
        Calendar cal01 = Calendar.getInstance();
        cal01.set(2015, Calendar.JANUARY, 1, 0, 0, 0);
        Calendar cal02 = Calendar.getInstance();
        cal02.set(2015, Calendar.JULY, 31, 0, 0, 0);
        Period p = new Period(cal01.getTime(), cal02.getTime());
        User u = new User();
        u.setId(1L);

        QueryResult qr = dao.list(0, 10, p, "Black", u);
        System.out.println("page: " + qr.getPager().toString());

        List<Order> oList = qr.getList(Order.class);
        System.out.println("oList.size() = " + oList.size());
    }
}
