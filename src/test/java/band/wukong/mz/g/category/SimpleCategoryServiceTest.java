package band.wukong.mz.g.category;

import band.wukong.mz.g.category.bean.Category;
import band.wukong.mz.g.category.service.SimpleCategoryService;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class SimpleCategoryServiceTest {
    private static final Log log = Logs.get();
    private Ioc ioc;
    private SimpleCategoryService service;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(SimpleCategoryService.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void testList() {
        List<Category> cateList = service.list("S");
        Assert.assertNotNull(cateList);
        log.debug("junit:\ncateList.size() = " + cateList.size());
        for (Category c : cateList) {
            System.out.println(c);
        }
    }

    @Test
    public void testListAllFlat() {
        Map<String, Category> cateMap = service.listAllFlat();
        Assert.assertTrue(null != cateMap && cateMap.size() > 0);
        System.out.println(cateMap.size());
    }
}
