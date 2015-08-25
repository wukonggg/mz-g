package band.wukong.mz.g.sku.module;

import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.g.category.bean.Category;
import band.wukong.mz.g.sku.SkuMoreHelper;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.bean.Sku;
import band.wukong.mz.g.sku.bean.SkuMore;
import band.wukong.mz.g.sku.bean.SkuPropType;
import band.wukong.mz.g.sku.service.GoodsService;
import band.wukong.mz.g.sku.service.SkuMoreViewService;
import band.wukong.mz.g.sku.service.SkuPropTypeService;
import band.wukong.mz.g.sku.service.SkuService;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean
@At("/stock/sku")
@Filters(@By(type = CheckSession.class, args = {"me", "/entry.io"}))
public class SkuModule {

    private static final Log log = Logs.get();
    private static final String GIMG_RELATIVE_PATH = "/gimg/sku/";
    public static final int SKU_ADD_S1_PAGE_SIZE = 4;

    @Inject
    private SkuService skuService;

    @Inject("refer:skuMoreViewService")
    private SkuMoreViewService smvService;

    @Inject("refer:goodsService")
    private GoodsService gService;

    @Inject("refer:skuPropTypeService")
    private SkuPropTypeService sptService;


    @At("/list")
    @Ok("jsp:view.sku.sku_list")
    public Object list(@Param("cateCode") String cateCode, @Param("qcond") String qcond, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {
        log.debug("Input params - cateCode: " + cateCode);
        log.debug("Input params - qcond: " + qcond);
        log.debug("Input params - pageNum: " + pageNum);
        log.debug("Input params - pageSize: " + pageSize);

        if (Strings.isBlank(qcond)) {
            qcond = "";
        }
        if (Strings.isBlank(cateCode)) {
            cateCode = Category.CATE_CODE_TYPE_SIMPLE;
        }

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("qcond", qcond);
        retMap.put("cateCode", cateCode);
        retMap.put("result", skuService.list(cateCode, qcond, pageNum, pageSize));
        return retMap;
    }

    @At("/add_s1")
    @Ok("jsp:view.sku.sku_add_s1")
    public Object add_s1(@Param("qcond") String qcond, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {
        log.debug("Input params - qcond: " + qcond);
        log.debug("Input params - pageNum: " + pageNum);
        log.debug("Input params - pageSize: " + pageSize);
        if (null == qcond) {
            qcond = "";
        }
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("qcond", qcond);
        retMap.put("result", gService.list(null, qcond, pageNum, SKU_ADD_S1_PAGE_SIZE));
        return retMap;
    }

    @At("/add/s2")
    @Ok("jsp:view.sku.sku_add_s2")
    public Object add_s2(@Param("..") Sku sc) {
        log.debug("Input params - Sku: \n" + sc);

        Goods g = gService.find(sc.getGoodsId());
        List<SkuPropType> sptList = sptService.listDistinctNameByCateCode(g.getCateCode());

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("g", g);
        retMap.put("sptList", sptList);
        return retMap;
    }


    @At("/save")
    @Ok("redirect:/stock/sku/list.io")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp"})
    public void save(@Param("..") Sku sku, @Param("inputFile") File img,
                     @Param("more") String more, HttpServletRequest req) throws IOException {
        log.debug("Input params - sku: \n" + sku);
        log.debug("Input params - image file is uploaded: " + Boolean.valueOf(null != img));
        log.debug("Input params - more: " + more);

        sku.setMoreList(SkuMoreHelper.convert2ListWhenSave(more));
        sku.setGimg(img);

        String path = req.getSession().getServletContext().getRealPath(GIMG_RELATIVE_PATH);
        skuService.saveWithMore(sku, path);
        //TODO FIXME 没有处理到异常。参考GoodsMoudle的save
        //顺手看看能不能把File参数去掉。参考goodsMoudle
    }

    @At("/mod")
    @Ok("jsp:view.sku.sku_mod")
    public Object mod(@Param("id") Long id) {
        log.debug("Input params - id: \n" + id);

        Sku sc = skuService.findWithLinks(id);
        List<SkuPropType> sptList = sptService.listDistinctNameByCateCode(sc.getGoods().getCateCode());

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("sc", sc);
        retMap.put("sptList", sptList);

        return retMap;
    }

    @At("/upd")
    @Ok("redirect:/stock/sku/list.io")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp"})
    public void upd(@Param("..") Sku sc, @Param("inputFile") File img,
                    @Param("more") String more, HttpServletRequest req) throws IOException {
        log.debug("Input params - sc: \n" + sc);
        log.debug("Input params - image file is uploaded: " + Boolean.valueOf(null != img));
        log.debug("Input params - more: " + more);

        if (sc.getId() <= 0) {
            throw new AppRuntimeException("木有找到你要更新的产品。。。");
        }

        sc.setGimg(img);
        sc.setMoreList(SkuMoreHelper.convert2ListWhenUpdate(more));

        String path = req.getSession().getServletContext().getRealPath(GIMG_RELATIVE_PATH);
        skuService.updateWithMore(sc, path);
        //TODO FIXME 没有处理到异常。参考GoodsMoudle的upd
        //顺手看看能不能把File参数去掉。参考goodsMoudle
    }

    @At("/rm")
    @Ok("redirect:/stock/sku/list.io")
    public void rm(@Param("id") Long id) {
        log.debug("Input params - id:" + id);

        if (null == id || id <= 0) {
            throw new AppRuntimeException("木有找到你要删除的产品SKU。。。");
        }

        skuService.rm(id);
    }

    @At("/rest")
    @Ok("jsp:view.sku.sku_rest")
    public Object rest(@Param("cateCodes") String[] cateCodes, @Param("counts") int[] counts
            ,@Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {

        if(null == cateCodes || cateCodes.length == 0) {
            cateCodes = loadSimpleCateCodes4rest();
        }
        if(null == counts || counts.length == 0) {
            counts = loadCounts4rest();
        }
        log.debug("Input params - cateCodes:" + Arrays.toString(cateCodes));
        log.debug("Input params - counts:" + Arrays.toString(counts));

        QueryResult qr = smvService.listSkuRest(cateCodes, counts, pageNum, pageSize);

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("cateCodes", Arrays.toString(cateCodes).replaceAll("\\[|\\]", ""));
        retMap.put("counts", Arrays.toString(counts).replaceAll("\\[|\\]", ""));
        retMap.put("result", qr);
        return retMap;

    }

    private String[] loadSimpleCateCodes4rest() {
        return new String[] {
                "S-1", "S-2", "S-3", "S-4", "S-5",
                "S-6", "S-7", "S-8", "S-9", "S-10",
                "S-11", "S-12", "S-13", "S-14"};
    }

    private int[] loadCounts4rest() {
        return new int[] {
                2, 2, 2, 2, 2,
                2, 2, 2, 2, 2,
                2, 2, 2, 2};
    }
}
