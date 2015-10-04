package band.wukong.mz.g.sale.dao.impl;

import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.dao.ItemDao;
import band.wukong.mz.nutz.test.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;

import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class ItemDaoImplTest {

    private Ioc ioc;
    private ItemDao itemDao;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        itemDao = ioc.get(ItemDao.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void insert_find() {
        Item item = new Item();
        item.setSkuid(12);
        item.setSkuMoreId(113);
        item.setCateCode("junit");
        item.setSprice(100);
        item.setDprice(100);
        item.setDcount(1);
        item.setPayment(100);
        item.setState(Item.STATE_OK);

        Item i1 = itemDao.insert(item);
        Assert.assertNotNull(i1);

        Item i2 = itemDao.find(i1.getId());
        Assert.assertNotNull(i2);
    }

    @Test
    public void find_update_find() {
        Item i1 = itemDao.find(11);

        String cc = "junit_update";
        i1.setCateCode(cc);
        itemDao.update(i1);

        Item i2 = itemDao.find(i1.getId());
        Assert.assertTrue(cc.equals(i2.getCateCode()));
    }

    @Test
    public void findWithOrder() {
        Item i1 = itemDao.findWithOrder(11);
        Assert.assertNotNull(i1);
        Assert.assertNull(i1.getOrder());

        Item i2 = itemDao.findWithOrder(5);
        Assert.assertNotNull(i2);
        Assert.assertNotNull(i2.getOrder());
    }

    @Test
    public void listWithSkuByOrder() {
        List<Item> items = itemDao.listWithSkuByOrder(1);
        Assert.assertTrue(items.size() == 2);
    }

    @Test
    public void listSameItems() {
        List<Item> items = itemDao.listSameItems(9);
        Assert.assertTrue(items.size() == 2);
    }

    @Test
    public void hasSkuBeenOrdered() {
        boolean has = itemDao.isSkuInOrder(1);
        Assert.assertTrue(has);
    }

}
