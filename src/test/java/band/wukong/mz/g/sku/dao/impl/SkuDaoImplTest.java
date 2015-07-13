package band.wukong.mz.g.sku.dao.impl;

import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.bean.Sku;
import band.wukong.mz.g.sku.bean.SkuMore;
import band.wukong.mz.g.sku.bean.SkuPropType;
import band.wukong.mz.g.sku.dao.GoodsDao;
import band.wukong.mz.g.sku.dao.SkuDao;
import band.wukong.mz.g.sku.service.SkuPropTypeService;
import band.wukong.mz.g.whisper.SimpleSidGenerator;
import band.wukong.mz.nutz.NutzTestHelper;
import band.wukong.mz.util.DateUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.Ioc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class SkuDaoImplTest {

    private Ioc ioc;
    private SkuDao skuDao;
    private SkuPropTypeService skuPropTypeService;
    private GoodsDao goodsDao;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        skuDao = ioc.get(SkuDao.class);
        skuPropTypeService = ioc.get(SkuPropTypeService.class);
        goodsDao = ioc.get(GoodsDao.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void insertWithMore_findWithLinks() {
        Date now = new Date();
        Sku sku = new Sku();
        sku.setSid("junit_sid_" + DateUtils.format(now));
        sku.setModel("海冰蓝");
        sku.setType("HEIGHT");
        sku.setPtime(now);
        sku.setPprice(300);
        sku.setSprice(500);
        sku.setImg("junit_img.png");
        sku.setCtime(now);
        sku.setState(Sku.STATE_ON);
        sku.setGoodsId(goodsDao.find("junit_米奇女童羽绒服").getId());

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

        Sku s = skuDao.insertWithMore(sku);
        Assert.assertNotNull(s);

        Sku s1 = skuDao.findWithLinks(s.getId());
        Assert.assertNotNull(s1);
        Assert.assertNotNull(s1.getGoods());
        Assert.assertNotNull(s1.getMoreList());
        Assert.assertEquals(s.getGoodsId(), s1.getGoodsId());
        Assert.assertEquals(sku.getMoreList().size(), s1.getMoreList().size());
        Assert.assertEquals(s.getMoreList().size(), s1.getMoreList().size());
    }

    @Test
    public void findWithLinks_rm() {
        Date now = new Date();
        Sku sku = skuDao.findWithLinks("junit_sid_" + DateUtils.format(now));
        skuDao.rm(sku.getId());

        Sku s1 = skuDao.findWithLinks(sku.getId());
        Assert.assertTrue(Sku.STATE_RM.equals(s1.getState()));
    }

    @Test
    public void findWithLinks_updateWithMore_findWithLinks() {
        Date now = new Date();
        Sku sku = skuDao.findWithLinks("junit_sid_" + DateUtils.format(now));
        sku.setUtime(now);
        for (SkuMore sm : sku.getMoreList()) {
            sm.setRemark("junit_remark_update");
        }
        skuDao.updateWithMore(sku);

        Sku s1 = skuDao.findWithLinks(sku.getId());
        Assert.assertTrue(sku.getUtime().toString().equals(s1.getUtime().toString()));
        for (SkuMore sm : s1.getMoreList()) {
            Assert.assertTrue("junit_remark_update".equals(sm.getRemark()));
        }

    }

    @Test
    public void list() {
        QueryResult qr1 = skuDao.list(SimpleCateConst.CATE_CODE_A_SYTZ, "", 0, 100);
        Assert.assertNotNull(qr1.getPager());
        Assert.assertTrue(qr1.getList(Sku.class).size() > 0);
        System.out.println("qr1.getPager() = " + qr1.getPager());
        System.out.println("qr1.getList(Sku.class).size() = " + qr1.getList(Sku.class).size());

        QueryResult qr2 = skuDao.list(SimpleCateConst.CATE_CODE_A_SYTZ, "junit", 0, 100);
        Assert.assertNotNull(qr2.getPager());
        Assert.assertTrue(qr2.getList(Sku.class).size() > 0);
        System.out.println("qr2.getPager() = " + qr2.getPager());
        System.out.println("qr2.getList(Sku.class).size() = " + qr2.getList(Sku.class).size());
    }


}
