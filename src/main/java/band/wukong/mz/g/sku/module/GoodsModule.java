package band.wukong.mz.g.sku.module;

import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.service.GoodsService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean
@At("/stock/goods")
@Filters(@By(type = CheckSession.class, args = {"me", "/entry.io"}))
public class GoodsModule {
    private static final Log log = Logs.get();
    private static final String GIMG_RELATIVE_PATH = "/gimg/goods/";

    @Inject
    private GoodsService goodsService;


    @At("/list")
    @Ok("jsp:view.sku.goods_list")
    public Object list(@Param("cateCode") String cateCode, @Param("qcond") String qcond, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {
        log.debug("Input params - cateCode: \n" + cateCode);
        log.debug("Input params - qcond: \n" + qcond);
        log.debug("Input params - pageNum: \n" + pageNum);
        log.debug("Input params - pageSize: \n" + pageSize);

        if (null == qcond) {
            qcond = "";
        }
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("cateCode", cateCode);
        retMap.put("qcond", qcond);
        retMap.put("result", goodsService.list(cateCode, qcond, pageNum, pageSize));
        return retMap;
    }

    @At("/add")
    @Ok("jsp:view.sku.goods_add")
    public void add() {
    }

    @At("/save")
    @Ok("raw")
    @Fail("json")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp"})
    public String save(@Param("..") Goods g, @Param("inputFile") File gimg, HttpServletRequest req) throws IOException {
        log.debug("Input params - goods: \n" + g);
        log.debug("Input params - gimg:");
        log.debug(null != gimg ? gimg.getName() : "NULL");

        g.setGimg(gimg);
        String path = req.getSession().getServletContext().getRealPath(GIMG_RELATIVE_PATH);
        goodsService.save(g, path);

        return "ok";
    }

    @At("/mod")
    @Ok("jsp:view.sku.goods_mod")
    public Goods mod(@Param("id") Long id) {
        log.debug("Input params - id: \n" + id);

        return goodsService.find(id);
    }

    @At("/upd")
    @Ok("raw")
    @Fail("json")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp"})
    public String upd(@Param("..") Goods g, @Param("inputFile") File gimg, HttpServletRequest req) throws IOException {
        log.debug("Input params - goods: \n" + g);
        log.debug("Input params - gimg:");
        log.debug(null != gimg ? gimg.getName() : "NULL");

        if (g.getId() <= 0) {
            throw new AppRuntimeException("木有找到你要更新的产品。。。");
        }

        if (null != gimg) {
            g.setGimg(gimg);
        }

        String path = req.getSession().getServletContext().getRealPath(GIMG_RELATIVE_PATH);
        goodsService.update(g, path);
        return "ok";
    }

    @At("/rm")
    @Ok("redirect:/stock/goods/list.io")
    public void rm(@Param("id") Long id) {
        log.debug("Input params - id:" + id);

        if (null == id || id <= 0) {
            throw new AppRuntimeException("木有找到你要删除的产品。。。");
        }

        goodsService.rm(id);
    }

}
