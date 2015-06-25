package band.wukong.mz.g.sku.module;

import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.sku.bean.SkuPropType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */

public class SkuPropTypeModuleTest {
    private static final Log log = Logs.get();

    private final String CONTEXT = "http://localhost:8080/g";
    private final int TIMEOUT = 5 * 1000;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void names() {
        String path = CONTEXT + "/sku/prop/names.io"
            + "?cateCode=" + SimpleCateConst.CATE_CODE_A_SYTZ
            + "&item=" + SkuPropType.ITEM_SIZE_STANDARD;
        log.debug("path=" + path);
        Response response = Http.get(path);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getContent());
        Assert.assertNotNull(response.getDetail());
        Assert.assertNotNull(response.getHeader());
        Assert.assertNotNull(response.getProtocal());
        Assert.assertTrue(response.getStatus() > 0);
        Assert.assertNotNull(response.getStream());
        System.out.println("response.getContent() = " + response.getContent());
        System.out.println("response.getDetail() = " + response.getDetail());
        System.out.println("response.getHeader() = " + response.getHeader());
        System.out.println("response.getProtocal() = " + response.getProtocal());
        System.out.println("response.getStatus() = " + response.getStatus());

        //CASE junit nutzmodule的单元测试 get请求
    }

    @Test
    public void list() {
        String path = CONTEXT + "/sku/prop/list.io";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("item", SkuPropType.ITEM_SIZE_STANDARD);
        params.put("name", "SIZE");
        params.put("cateCode", SimpleCateConst.CATE_CODE_A_SYTZ);

        String resp = Http.post(path, params, TIMEOUT);
        Assert.assertNotNull(resp);
        System.out.println("resp = " + resp);

        //CASE junit:nutz nutzmodule的单元测试 post请求
    }

}
