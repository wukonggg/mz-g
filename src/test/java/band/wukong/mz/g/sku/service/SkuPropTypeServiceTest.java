package band.wukong.mz.g.sku.service;

import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.sku.bean.SkuPropType;
import band.wukong.mz.nutz.NutzTestHelper;
import com.alibaba.fastjson.JSONArray;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;

import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class SkuPropTypeServiceTest {
    private Ioc ioc;
    private SkuPropTypeService service;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(SkuPropTypeService.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void listDistinctNameByItem() {
        List<SkuPropType> sdList = service.listDistinctNameByCateCode(
                SimpleCateConst.CATE_CODE_A_SYTZ);

        Assert.assertNotNull(sdList);
        System.out.println("JSONArray.toJSON(sdList) = " + JSONArray.toJSON(sdList));
    }

    @Test
    public void list() {
        SkuPropType sd = new SkuPropType();
        sd.setItem(SkuPropType.ITEM_SIZE_STANDARD);
        sd.setName("SIZE");
        sd.setCateCode(SimpleCateConst.CATE_CODE_A_SYTZ);

        List<SkuPropType> sdList = service.list(sd);
        Assert.assertNotNull(sdList);
        System.out.println("JSONArray.toJSON(sdList) = " + JSONArray.toJSON(sdList));
    }

}
