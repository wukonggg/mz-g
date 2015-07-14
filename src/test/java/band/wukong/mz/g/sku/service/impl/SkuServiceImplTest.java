package band.wukong.mz.g.sku.service.impl;

import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.sku.bean.Sku;
import band.wukong.mz.g.sku.bean.SkuMore;
import band.wukong.mz.g.sku.bean.SkuPropType;
import band.wukong.mz.g.sku.service.GoodsService;
import band.wukong.mz.g.sku.service.SkuPropTypeService;
import band.wukong.mz.g.sku.service.SkuService;
import band.wukong.mz.nutz.NutzTestHelper;
import band.wukong.mz.util.DateUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class SkuServiceImplTest {
    private static final Log log = Logs.get();
    private Ioc ioc;
    private SkuService skuService;
    private SkuPropTypeService skuPropTypeService;
    private GoodsService goodsService;
    private String path_client;
    private String path_server;

    @Before
    public void setUp() {
        log.debug("set up...");
        ioc = NutzTestHelper.createIoc();
        skuService = ioc.get(SkuService.class);
        skuPropTypeService = ioc.get(SkuPropTypeService.class);
        goodsService = ioc.get(GoodsService.class);
        path_client = "C:\\Users\\Johnson\\Desktop";
        path_server = "C:\\Users\\Johnson\\Desktop";

    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
        log.debug("tear down...");
    }

    @Test
    public void saveWithMore_findWithLinks() {
        Date now = new Date();
        Sku sku = new Sku();
        sku.setModel("junit_海冰蓝");
        sku.setType("HEIGHT");
        sku.setPtime(now);
        sku.setPprice(300);
        sku.setSprice(500);
        sku.setCtime(now);
        sku.setState(Sku.STATE_ON);
        sku.setGoodsId(goodsService.find("junit_米奇女童羽绒服").getId());
        sku.setGimg(new File(path_client + "\\lin.png"));

        SkuPropType spt = new SkuPropType();
        spt.setCateCode(SimpleCateConst.CATE_CODE_A_SYTZ);
        spt.setItem(SkuPropType.ITEM_SIZE_STANDARD);
        spt.setName("SIZE");
        List<SkuPropType> sptList = skuPropTypeService.list(spt);
        List<SkuMore> smList = new ArrayList<>();
        for (SkuPropType lin: sptList) {
            SkuMore sm = new SkuMore();
            sm.setSize(lin.getEvalue());
            sm.setCount(99);
            sm.setRemark("junit_remark");
            smList.add(sm);
        }
        sku.setMoreList(smList);

        Sku s = skuService.saveWithMore(sku, path_server);
        Assert.assertNotNull(s);

        Sku s1 = skuService.findWithLinks(s.getId());
        Assert.assertNotNull(s1);
        Assert.assertNotNull(s1.getGoods());
        Assert.assertNotNull(s1.getMoreList());
        Assert.assertEquals(s.getGoodsId(), s1.getGoodsId());
        Assert.assertEquals(sku.getMoreList().size(), s1.getMoreList().size());
        Assert.assertEquals(s.getMoreList().size(), s1.getMoreList().size());
    }

    @Test
    public void findWithLinks_updateWithMore_findWithLinks() {
        Sku sku = skuService.findWithLinks(22L);
        for (SkuMore sm : sku.getMoreList()) {
            sm.setRemark("junit_remark_update...");
        }
        skuService.updateWithMore(sku, path_server);

        Sku s1 = skuService.findWithLinks(sku.getId());
        Assert.assertNotNull(s1);
        Assert.assertNotNull(s1.getMoreList());
        for (SkuMore sm : s1.getMoreList()) {
            Assert.assertTrue("junit_remark_update...".equals(sm.getRemark()));
        }

    }


    @Test
    public void updateWithMore_rm() {
        Sku sku = skuService.findWithLinks(22L);
        skuService.rm(sku.getId());

        Sku s1 = skuService.findWithLinks(sku.getId());
        Assert.assertTrue(Sku.STATE_RM.equals(s1.getState()));
    }

    @Test
    public void list() {
        QueryResult qr1 = skuService.list(SimpleCateConst.CATE_CODE_A_SYTZ, "", 0 , 100);
        Assert.assertNotNull(qr1.getPager());
        Assert.assertNotNull(qr1.getList(Sku.class));
        Assert.assertTrue(qr1.getList(Sku.class).size() > 0);

        QueryResult qr2 = skuService.list(SimpleCateConst.CATE_CODE_A_SYTZ, "10", 0 , 100);
        Assert.assertNotNull(qr2.getPager());
        Assert.assertNotNull(qr2.getList(Sku.class));
        Assert.assertTrue(qr2.getList(Sku.class).size() > 0);
    }
}
