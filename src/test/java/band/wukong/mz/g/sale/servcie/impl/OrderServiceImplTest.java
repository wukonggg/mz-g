package band.wukong.mz.g.sale.servcie.impl;

import band.wukong.mz.base.bean.Period;
import band.wukong.mz.common.privilege.bean.User;
import band.wukong.mz.g.AppConst;
import band.wukong.mz.g.sale.bean.Cart;
import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.bean.Order;
import band.wukong.mz.g.sale.service.CartService;
import band.wukong.mz.g.sale.service.OrderService;
import band.wukong.mz.g.sku.dao.SkuMoreDao;
import band.wukong.mz.nutz.test.NutzTestHelper;
import band.wukong.util.DateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.Ioc;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class OrderServiceImplTest {

    private Ioc ioc;
    private OrderService orderService;
    private SkuMoreDao skuMoreDao;
    private CartService cartService;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        orderService = ioc.get(OrderService.class);
        skuMoreDao = ioc.get(SkuMoreDao.class);
        cartService = ioc.get(CartService.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }


    @Test
    public void find() {
        Assert.assertNotNull(orderService.find(1));
    }

    @Test
    public void listDetail() {
        String qcond = "99999999998";   //cid
        Period period = new Period(DateUtil.convert2date("2015-01-01"), DateUtil.convert2date("2015-07-20"));
        User user = new User();
        user.setId(1L);
        QueryResult qr = orderService.listDetail(qcond, period, user, 0, AppConst.PAGE_NUM_DFT);
        Assert.assertNotNull(qr.getPager().getRecordCount());
        Assert.assertTrue(qr.getList(Order.class).size() > 0);
    }

    @Test
    public void pay() {
        // 1、pay方法是否返回了新生成的order
        // 2、库存数量变化
        // 3、购物车变化


        final Long CUST_ID = 2L;
        final Long CUST_CID = 99999999999L;
        final Long USER_ID = 1L;
        final Long SKU_MORE_ID = 93L;
        final int SPRICE = 120;
        final int DCOUNT = 1;
        final double PAYMENT = 108;


        int pre_skuMore_count = skuMoreDao.find(SKU_MORE_ID).getCount();

        Cart[] carts = new Cart[1];
        Cart cart = new Cart();
        cart.setCustId(CUST_ID);
        cart.setSkuMoreId(SKU_MORE_ID);
        cart.setCount(DCOUNT);
        cart.setSprice(SPRICE);
        cart.setCount(DCOUNT);
        cart.setPayment(PAYMENT);
        carts[0] = cart;
        Order order = orderService.pay(carts, USER_ID);

        // 1、pay方法是否返回了新生成的order
        Assert.assertNotNull(order);
        Assert.assertTrue(order.getItems().size() == 1);

        // 2、库存数量变化
        int curr_skuMore_count = skuMoreDao.find(SKU_MORE_ID).getCount();
        Assert.assertTrue(pre_skuMore_count == curr_skuMore_count + DCOUNT);

        // 3、购物车变化
        Map<String, List<Cart>> userCartsMap= cartService.listGroupByCust(USER_ID);
        Assert.assertTrue(null != userCartsMap);
        List<Cart> cartsOfCust = userCartsMap.get(CUST_CID);
        if (null != cartsOfCust) {
            for (Cart c : cartsOfCust) {
                // 如果找到了key为cid的CartList中有skuMoreId，说明没有删掉
                Assert.assertTrue(SKU_MORE_ID != c.getSkuMoreId());
            }
        }   // 如果carsOfCust为null，则说明ok。所以就不用else了。

        // FIXME 补充失败情况的单元测试用例
        // 1、用户是否存在
        // 2、顾客是否存在
        // 3、购买数量大于
    }

    @Test
    public void findItemWithOrder() {
        Item item = orderService.findItemWithOrder(9);
        Assert.assertNotNull(item);
        Assert.assertNotNull(item.getOrder());
    }

    @Test
    public void returnItem() {
        //1、准备测试数据
        //2、记录原始数据：order.item.state/dcount/payment,
        //               order.item.skuMore(skukMoreId).count,
        //               order.cust.paymentClothing
        //3、执行待测方法
        //4、检查变化情况2：新的item：  order.item.state/dcount/payment/returnTime/returnReason/returnDesc
        //5、检查变化情况3：库存：      order.item.skuMore(skukMoreId).count
        //6、检查变化情况4：cust：     order.cust.paymentClothing


        //1、准备测试数据
        final int DT_ITEM_ID = 9;
        final int DT_USERID = 1;
        final int DT_RET_COUNT = 1;
        final String DT_RET_REASON = Item.RETURN_REASON_SIZE;
        final String DT_RET_DESC = "就不告诉你。。。";
        Item i = new Item();
        i.setId(DT_ITEM_ID);
        i.setReturnUserId(DT_USERID);
        i.setDcount(DT_RET_COUNT);
        i.setReturnReason(DT_RET_REASON);
        i.setReturnDesc(DT_RET_DESC);

        //2、记录原始数据：order.item.state/dcount/payment
        Item item_original = orderService.findItemWithOrder(DT_ITEM_ID);
        long skuMore_original_count = skuMoreDao.find(item_original.getSkuMoreId()).getCount();


        //3、执行待测方法
        Item item_returned = orderService.returnItem(i);

        //4、检查变化情况2：新的item：  order.item.state/dcount/payment/returnTime/returnReason/returnDesc
        Assert.assertEquals(DT_RET_REASON, item_returned.getState());
        Assert.assertEquals(DT_RET_COUNT, -item_returned.getDcount());
        Assert.assertEquals(item_returned.getDprice() * item_returned.getDcount(), item_returned.getPayment());
        Assert.assertTrue(DateUtil.isSameDay(new Date(), item_returned.getReturnTime()));
        Assert.assertTrue(DT_RET_REASON.equals(item_returned.getReturnReason()));
        Assert.assertTrue(DT_RET_DESC.equals(item_returned.getReturnDesc()));


        //5、检查变化情况3：库存：      order.item.skuMore(skukMoreId).count
        Assert.assertEquals(item_returned.getSkuMoreId(), item_original.getSkuMoreId());
        long skuMore_now_count = skuMoreDao.find(item_returned.getSkuMoreId()).getCount();
        Assert.assertEquals(skuMore_original_count, skuMore_now_count + item_returned.getDcount()); //return的dcount是负值
        

    }
}
