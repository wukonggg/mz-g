package band.wukong.mz.g.sale.servcie.impl;

import band.wukong.mz.g.customer.service.CustomerService;
import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.service.impl.OrderServiceImpl;
import band.wukong.mz.g.sku.dao.SkuMoreDao;
import band.wukong.mz.g.sku.dao.impl.SkuMoreDaoImpl;
import band.wukong.mz.nutz.NutzTestHelper;
import band.wukong.mz.util.DateUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;

import java.util.Date;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class OrderServiceImplTest {

    private Ioc ioc;
    private OrderServiceImpl orderService;
    private SkuMoreDao skuMoreDao;
    private CustomerService custService;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        orderService = ioc.get(OrderServiceImpl.class);
        skuMoreDao = ioc.get(SkuMoreDaoImpl.class);
        custService = ioc.get(CustomerService.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
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

        //2、记录原始数据：order.item.state/dcount/payment, order.cust.paymentClothing
        Item item_original = orderService.findItemWithOrder(DT_ITEM_ID);
        long custId = orderService.find(item_original.getOid()).getCustId();
        long item_original_paymentClothing = custService.find(custId).getPaymentClothing();
        long skuMore_original_count = skuMoreDao.find(item_original.getSkuMoreId()).getCount();


        //3、执行待测方法
        Item item_returned = orderService.returnItem(i);

        //4、检查变化情况2：新的item：  order.item.state/dcount/payment/returnTime/returnReason/returnDesc
        Assert.assertEquals(DT_RET_REASON, item_returned.getState());
        Assert.assertEquals(DT_RET_COUNT, -item_returned.getDcount());
        Assert.assertEquals(item_returned.getDprice() * item_returned.getDcount(), item_returned.getPayment());
        Assert.assertTrue(DateUtils.isSameDay(new Date(), item_returned.getReturnTime()));
        Assert.assertTrue(DT_RET_REASON.equals(item_returned.getReturnReason()));
        Assert.assertTrue(DT_RET_DESC.equals(item_returned.getReturnDesc()));


        //5、检查变化情况3：库存：      order.item.skuMore(skukMoreId).count
        Assert.assertEquals(item_returned.getSkuMoreId(), item_original.getSkuMoreId());
        long skuMore_now_count = skuMoreDao.find(item_returned.getSkuMoreId()).getCount();
        Assert.assertEquals(skuMore_original_count, skuMore_now_count + item_returned.getDcount()); //return的dcount是负值

        //6、检查变化情况4：cust：     order.cust.paymentClothing
        long next_paymentclothing = custService.find(custId).getPaymentClothing();
        Assert.assertEquals(item_original_paymentClothing + item_returned.getPayment(), next_paymentclothing);

    }
}
