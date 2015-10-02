package band.wukong.mz.g.gallery.module;

import band.wukong.mz.g.gallery.service.GalleryServive;
import band.wukong.mz.g.sku.bean.Goods;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean
@At("/gallery")
public class GalleryModule {

    @Inject
    private GalleryServive galleryServive;

    /**
     * 到LANDING画面
     */
    @At("/landing")
    @Ok("jsp:view.gallery.landing")
    public void landing() {
    }

    @At("/search")
    @Ok("jsp:view.gallery.search")
    @Filters(@By(type = CheckSession.class, args = {"me", "/entry.io"}))
    public Map<String, Object> search(@Param("kw") String kw) {

        List<Goods> glist = galleryServive.search(kw);

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("kw", kw);
        retMap.put("glist", glist);

        return retMap;

    }

}
