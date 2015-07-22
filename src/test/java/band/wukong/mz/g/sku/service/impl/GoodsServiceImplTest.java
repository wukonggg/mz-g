package band.wukong.mz.g.sku.service.impl;

import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.category.bean.Category;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.service.GoodsService;
import band.wukong.mz.nutz.NutzTestHelper;
import band.wukong.util.DateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class GoodsServiceImplTest {
    private static final Log log = Logs.get();
    private Ioc ioc;
    private GoodsService service;
    private String path_client;
    private String path_server;

    @Before
    public void setUp() {
        log.debug("set up...");
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(GoodsService.class);
        path_client = "C:\\Users\\Johnson\\Desktop";
        path_server = "C:\\Users\\Johnson\\Desktop";

    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
        log.debug("tear down...");
    }

    @Test
    public void save_find() {
        Goods g = new Goods();
        g.setGname("junit_" + DateUtil.format(new Date()));
        g.setCateCode(SimpleCateConst.CATE_CODE_A_SYTZ);
        g.setGimg(new File(path_client + "\\lin.png"));

        Goods g1 = service.save(g, path_server);
        Assert.assertNotNull(g1);
        Assert.assertNotNull(g1.getImg());
        Assert.assertTrue(new File(path_server + "\\" + g1.getImg()).exists());

        Goods g2 = service.find(g1.getId());
        Assert.assertNotNull(g2);
    }

    @Test
    public void find_update_find() {
        Goods g = new Goods();
        g.setGname("junit_" + DateUtil.format(new Date()));

        Goods g1 = service.find(g.getGname());
        Assert.assertNotNull(g1);

        g1.setWords("junittt...");
        g1.setGimg(new File(path_client + "\\lin.png"));
        service.update(g1, path_server);

        Goods g2 = service.find(g1.getId());
        Assert.assertNotNull(g2);
        Assert.assertTrue(new File(path_server + "\\" + g2.getImg()).exists());
    }

    @Test
    public void find_rm_find() {
        Goods g = new Goods();
        g.setGname("junit_" + DateUtil.format(new Date()));

        Goods g1 = service.find(g.getGname());
        Assert.assertNotNull(g1);

        service.rm(g1.getId());

        Goods g2 = service.find(g1.getId());
        Assert.assertEquals(Goods.STATE_RM, g2.getState());

    }

    @Test
    public void list() {
        QueryResult qr = service.list(SimpleCateConst.CATE_CODE_A_SYTZ, "", 0, 10);
        List<Goods> glist = qr.getList(Goods.class);
        Assert.assertTrue(glist.size() > 0);
    }
}
