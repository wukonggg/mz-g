package band.wukong.mz.g.sku.module;

import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.g.AppConf;
import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.category.bean.Category;
import band.wukong.mz.g.sku.SkuMoreHelper;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.bean.Sku;
import band.wukong.mz.g.sku.bean.SkuPropType;
import band.wukong.mz.g.sku.service.GoodsService;
import band.wukong.mz.g.sku.service.SkuMoreViewService;
import band.wukong.mz.g.sku.service.SkuPropTypeService;
import band.wukong.mz.g.sku.service.SkuService;
import band.wukong.practice.barcode.zxing.BarcodeTool;
import com.google.zxing.BarcodeFormat;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

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

    @Inject("app_conf")
    private AppConf appConf;

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
    @Ok("json")
    @Fail("json")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp"})
    public Object save(@Param("..") Sku sku, @Param("inputFile") File img,
                     @Param("more") String more, HttpServletRequest req) throws IOException {
        log.debug("Input params - sku: \n" + sku);
        log.debug("Input params - image file is uploaded: " + Boolean.valueOf(null != img));
        log.debug("Input params - more: " + more);

        NutMap re = new NutMap();

        sku.setMoreList(SkuMoreHelper.convert2ListWhenSave(more));
        sku.setGimg(img);
        String path = req.getSession().getServletContext().getRealPath(GIMG_RELATIVE_PATH);
        try {
            skuService.saveWithMore(sku, path);
        } catch (AppRuntimeException e) {
            return re.setv("ok", false).setv("msg", e.getMessage());
        }

//        return for test
//        return re.setv("ok", false).setv("msg", "lalalalld");
        return re.setv("ok", true);
    }

    @At("/printBarcode")
    @Ok("raw:txt")
    public Object printBarcode(@Param("barcode") String barcode) {
        log.debug("Input params - barcode: \n" + barcode);

        int width = appConf.getInt(AppConf.BARCODE_IMAGE_WIDTH);
        int height = appConf.getInt(AppConf.BARCODE_IMAGE_HEIGHT);
        String format = appConf.get(AppConf.BARCODE_IMAGE_FORMAT);
        String way = appConf.get(AppConf.BARCODE_IMAGE_PATH);
        way = way.replace("$name", UUID.randomUUID().toString());

        FileSystem fileSystem = FileSystems.getDefault();
        Path path = fileSystem.getPath(way);

        BarcodeTool.encode(barcode, BarcodeFormat.CODE_128, width, height, path, format);

        return Files.findFile(way);
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
    @Ok("json")
    @Fail("json")
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp"})
    public Object upd(@Param("..") Sku sc, @Param("inputFile") File img,
                    @Param("more") String more, HttpServletRequest req) throws IOException {
        log.debug("Input params - sc: \n" + sc);
        log.debug("Input params - image file is uploaded: " + Boolean.valueOf(null != img));
        log.debug("Input params - more: " + more);

        NutMap re = new NutMap();

        if (sc.getId() <= 0) {
            throw new AppRuntimeException("木有找到你要更新的产品。。。");
        }
        sc.setGimg(img);
        sc.setMoreList(SkuMoreHelper.convert2ListWhenUpdate(more));


        String path = req.getSession().getServletContext().getRealPath(GIMG_RELATIVE_PATH);
        try {
            skuService.updateWithMore(sc, path);
        } catch (AppRuntimeException e) {
            return re.setv("ok", false).setv("msg", e.getMessage());
        }
//        return for test
//        return re.setv("ok", false).setv("msg", "lalalalld");
        return re.setv("ok", true);
    }

    @At("/rm")
    @Ok("json")
    @Fail("json")
//    @Ok("redirect:/stock/sku/list.io")
    public Object rm(@Param("skuId") Long id) {
        log.debug("Input params - skuId:" + id);

        if (null == id || id <= 0) {
            throw new AppRuntimeException("木有找到你要删除的产品SKU。。。");
        }

        NutMap re = new NutMap();

        try {
            skuService.rm(id);
        } catch (Exception e) {
            if (e instanceof AppRuntimeException) {
                return re.setv("ok", false).setv("msg", e.getMessage());
            } else {
                return re.setv("ok", false).setv("msg", "你从火星来的吗？？？");
            }
        }

        return re.setv("ok", true);
    }

    @At("/rest")
    @Ok("jsp:view.sku.sku_rest")
    public Object rest(@Param("cateCodes") String[] cateCodes, @Param("counts") int[] counts
            ,@Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {

        if(null == cateCodes || cateCodes.length == 0) {
            cateCodes = loadSimpleCateCodes4rest();
        }
        if(null == counts || counts.length == 0) {
            counts =  new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
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
        return new String[] {SimpleCateConst.CATE_CODE_A_SYTZ, SimpleCateConst.CATE_CODE_B_KZ
                , SimpleCateConst.CATE_CODE_C_NY, SimpleCateConst.CATE_CODE_D_XZ, SimpleCateConst.CATE_CODE_E_RH
                , SimpleCateConst.CATE_CODE_F_LSBJ, SimpleCateConst.CATE_CODE_G_NF, SimpleCateConst.CATE_CODE_H_WJ
                , SimpleCateConst.CATE_CODE_I_NBS, SimpleCateConst.CATE_CODE_J_WJ
        };
    }

}
