package band.wukong.mz.g.sku.service;

import band.wukong.mz.g.sku.bean.SkuMoreView;
import band.wukong.mz.nutz.test.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.Ioc;

import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class SkuMoreViewServiceTest {
    private Ioc ioc;
    private SkuMoreViewService service;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(SkuMoreViewService.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void list() {
        QueryResult qr1 = service.list("", 0, 0);
        Assert.assertNotNull(qr1.getPager());
        Assert.assertNotNull(qr1.getList(SkuMoreView.class));
        System.out.println("qr1.getPager() = " + qr1.getPager());
        System.out.println("qr1.getList(SkuMoreView.class).size() = " + qr1.getList(SkuMoreView.class).size());

        QueryResult qr2 = service.list("05 06", 0, 0);
        Assert.assertNotNull(qr2.getPager());
        Assert.assertNotNull(qr2.getList(SkuMoreView.class));
        System.out.println("qr2.getPager() = " + qr2.getPager());
        System.out.println("qr2.getList(SkuMoreView.class).size() = " + qr2.getList(SkuMoreView.class).size());
    }

    @Test
    public void find() {
        Assert.assertNotNull(service.find(97));
    }

    @Test
    public void listSkuRest() {
        String[] cateCodes = {"S-1", "S-2", "S-3", "S-4"};
        int[] counts = {8, 8, 8, 8};
        QueryResult qr = service.listSkuRest(cateCodes, counts, 0, 10);

        System.out.println("qr.getPager() = " + qr.getPager());
        List<SkuMoreView> smvs = qr.getList(SkuMoreView.class);
        for (SkuMoreView smv : smvs) {
            System.out.println(smv);
        }
        Assert.assertTrue(smvs.size() == 2);
    }
}
