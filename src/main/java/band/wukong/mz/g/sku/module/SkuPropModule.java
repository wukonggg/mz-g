package band.wukong.mz.g.sku.module;

import band.wukong.mz.g.sku.SkuMoreHelper;
import band.wukong.mz.g.sku.bean.SkuPropType;
import band.wukong.mz.g.sku.service.SkuPropTypeService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean
@At("/sku/prop")
@Ok("json")
@Fail("json")
@Filters(@By(type = CheckSession.class, args = {"me", "/entry.io"}))
public class SkuPropModule {
    private static final Log log = Logs.get();

    @Inject("refer:skuPropTypeService")
    private SkuPropTypeService service;

    @At("/list")
    public Object list(@Param("..") SkuPropType sd) {
        log.debug("Input params: sd = " + sd);
        return service.list(sd);
    }

    @At("/loadTypeTemplate")
    @Ok("raw")
    public String loadTypeTemplate(@Param("..") SkuPropType spt) {
        log.debug("Input params: spt = " + spt);
        List<SkuPropType> sdList = service.list(spt);

        return SkuMoreHelper.convertSkuDict2Text(sdList);
    }


}
