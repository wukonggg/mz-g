package band.wukong.mz.g.sku.service;

import band.wukong.mz.g.sku.bean.SkuMoreView;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
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
        QueryResult qr = service.list("562dc76e", 0, 0);
        List<SkuMoreView> smvList = qr.getList(SkuMoreView.class);
        Pager pager = qr.getPager();
        Assert.assertNotNull(smvList);
        System.out.println("smvList.size() = " + smvList.size());
        System.out.println("pager = " + pager);
    }


}
