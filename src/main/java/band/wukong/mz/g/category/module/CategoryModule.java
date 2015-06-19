package band.wukong.mz.g.category.module;

import band.wukong.mz.g.category.service.SimpleCategoryService;
import com.alibaba.fastjson.JSONArray;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean
@At("/category")
@Ok("json")
@Fail("json")
public class CategoryModule {

    private static final Log log = Logs.get();

    @Inject("refer:cateService")
    private SimpleCategoryService service;

    @At("/list")
    public Object list(@Param("pcode") String pcode) {
        log.debug("Input params: pcode = " + pcode);

        return JSONArray.toJSON(service.list(pcode));
    }
}
