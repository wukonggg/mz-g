package band.wukong.mz.g.sku.dao.impl;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.dao.GoodsDao;
import band.wukong.mz.nutz.test.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.Ioc;

import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class GoodsDaoImplTest {
    private Ioc ioc;
    private GoodsDao goodsDao;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        goodsDao = ioc.get(GoodsDao.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void insert_find() {
        Goods g = new Goods();
        g.setCateCode(SimpleCateConst.CATE_CODE_A_SYTZ);
        g.setGname("junit_米奇女童羽绒服");
        g.setState(Goods.STATE_OK);

        Goods g1 = goodsDao.insert(g);
        Goods g2 = goodsDao.find(g1.getId());
        Assert.assertEquals(g1.getId(), g2.getId());
    }

    @Test(expected = IllegalParameterException.class)
    public void insert_valid_failed() {
        Goods g = new Goods();
        g.setCateCode(SimpleCateConst.CATE_CODE_A_SYTZ);
        g.setGname("junit_米奇女童羽绒服");
        Goods g1 = goodsDao.insert(g);
        Assert.assertEquals(g.getGname(), g1.getGname());
    }

    @Test
    public void find_update() {
        Goods g = new Goods();
        g.setGname("junit_米奇女童羽绒服");

        Goods g1 = goodsDao.find(g.getGname());
        g1.setWords("junit hahaha");
        goodsDao.update(g1);
        Goods g2 = goodsDao.find(g1.getId());

        Assert.assertEquals(g1.getWords(), g2.getWords());
    }

    @Test
    public void find_rm_find() {
        Goods g = new Goods();
        g.setGname("junit_米奇女童羽绒服");

        Goods g1 = goodsDao.find(g.getGname());
        goodsDao.rm(g1.getId());

        Goods g2 = goodsDao.find(g1.getId());
        Assert.assertEquals(Goods.STATE_RM, g2.getState());
    }

    @Test
    public void testList() {
        QueryResult qr = goodsDao.list(SimpleCateConst.CATE_CODE_A_SYTZ, "", 0, 0);
        List<Goods> glist = qr.getList(Goods.class);
        Pager pager = qr.getPager();
        Assert.assertNotNull(pager);
        Assert.assertNotNull(glist);
    }

}
