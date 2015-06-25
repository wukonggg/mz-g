package band.wukong.mz.g.sale.dao.impl;

import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
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
    private ItemDaoImpl dao;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        dao = ioc.get(ItemDaoImpl.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void list() {
        List<Item> items = dao.listWithSkuByOrder(1);
        for(Item item : items) {
            System.out.println("item = " + item);
        }

    }

}
