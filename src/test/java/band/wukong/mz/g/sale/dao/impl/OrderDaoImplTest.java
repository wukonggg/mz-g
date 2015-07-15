package band.wukong.mz.g.sale.dao.impl;

import band.wukong.mz.base.bean.Period;
import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.privilege.bean.User;
import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.bean.Order;
import band.wukong.mz.g.sale.dao.OrderDao;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.Ioc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class OrderDaoImplTest {
    private Ioc ioc;
    private OrderDao orderDao;

    private static final long USER_ID = 3;
    private static final long CUST_ID = 1;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        orderDao = ioc.get(OrderDao.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void insertWithItems_findWithLinks() {
        Order order = new Order();
        order.setCustId(CUST_ID);
        order.setDtime(new Date());
        order.setUserId(USER_ID);

        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setSkuid(17);
        item.setSkuMoreId(140);
        item.setCateCode(SimpleCateConst.CATE_CODE_A_SYTZ);
        item.setSprice(120);
        item.setDprice(120);
        item.setDcount(1);
        item.setPayment(120);
        item.setState(Item.STATE_OK);
        items.add(item);
        order.setItems(items);

        Order o1 = orderDao.insertWithItems(order);
        Assert.assertNotNull(o1);
        Assert.assertNotNull(o1.getItems());
        Assert.assertTrue(o1.getItems().size() == 1);

        Order o2 = orderDao.findWithLinks(o1.getId());
        Assert.assertNotNull(o2);
        Assert.assertNotNull(o2.getItems());
        Assert.assertTrue(o2.getItems().size() == 1);
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

        QueryResult qr = orderDao.list(0, 10, p, "Black", u);
        System.out.println("page: " + qr.getPager().toString());

        List<Order> oList = qr.getList(Order.class);
        System.out.println("oList.size() = " + oList.size());
    }
}
