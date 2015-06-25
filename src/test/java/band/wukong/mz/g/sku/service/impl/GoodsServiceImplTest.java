package band.wukong.mz.g.sku.service.impl;

import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.service.GoodsService;
import band.wukong.mz.nutz.NutzTestHelper;
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
public class GoodsServiceImplTest {
    private Ioc ioc;
    private GoodsService service;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(GoodsService.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void tesstList() {
        QueryResult qr = service.list(SimpleCateConst.CATE_CODE_A_SYTZ, "", 0, 10);
        List<Goods> glist = qr.getList(Goods.class);
        Assert.assertTrue(glist.size() > 0);
    }
}
