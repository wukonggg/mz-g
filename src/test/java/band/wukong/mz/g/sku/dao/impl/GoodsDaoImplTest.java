package band.wukong.mz.g.sku.dao.impl;

import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.dao.GoodsDao;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.Ioc;

import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class GoodsDaoImplTest {
    private Ioc ioc;
    private GoodsDao dao;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        dao = ioc.get(GoodsDao.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void tesstInsert() {
        Goods g = newBean();
        Goods g1 = dao.insert(g);
        Assert.assertEquals(g.getGname(), g1.getGname());
    }

    @Test
    public void testFind() {
        Goods g = newBean();
        g = dao.insert(g);
        Goods g1 = dao.find(g.getId());
        Assert.assertNotNull(g1);

        Goods g2 = dao.find(99999999L);
        Assert.assertNull(g2);
    }

    @Test
    public void testUpdate() {
        Goods g = newBean();
        g = dao.insert(g);

        Goods g1 = dao.find(g.getId());
        String newGname = "junit gname update!";
        g1.setGname(newGname);
        dao.update(g1);

        Goods g2 = dao.find(g1.getId());
        Assert.assertEquals(newGname, g2.getGname());
    }

    @Test
    public void testRm() {
        Goods g = newBean();

        Goods g1 = dao.insert(g);
        dao.rm(g1.getId());

        Goods g2 = dao.find(g1.getId());
        Assert.assertEquals(Goods.STATE_RM, g2.getState());
    }

    @Test
    public void testList() {
        Goods g = newBean();
        QueryResult qr = dao.list(SimpleCateConst.CATE_CODE_A_SYTZ, "", 0, 0);
        @SuppressWarnings("unchecked")
        List<Goods> glist = qr.getList(Goods.class);
        Pager pager = qr.getPager();
        System.out.println("glist.size() = " + glist.size());
        System.out.println("pager = " + pager);
    }

    private Goods newBean() {
        Goods g = new Goods();
        g.setCateCode(SimpleCateConst.CATE_CODE_A_SYTZ);
        g.setGname("junit_米奇女童羽绒服");
        g.setImg("junit_img.png");
        g.setWords("junit_words");
        g.setCtime(new Date());
        g.setState(Goods.STATE_OK);
        return g;
    }

}
