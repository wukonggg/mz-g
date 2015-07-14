package band.wukong.mz.g.sku.service.impl;

import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.category.service.SimpleCategoryService;
import band.wukong.mz.g.sale.OutOfStockException;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.bean.Sku;
import band.wukong.mz.g.sku.bean.SkuMore;
import band.wukong.mz.g.sku.dao.SkuDao;
import band.wukong.mz.g.sku.dao.SkuMoreDao;
import band.wukong.mz.g.sku.service.GoodsService;
import band.wukong.mz.g.sku.service.SidGenerator;
import band.wukong.mz.g.sku.service.SkuService;
import band.wukong.mz.util.FileUtils;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "skuService")
public class SkuServiceImpl implements SkuService {

    private static final Log log = Logs.get();

    @Inject
    private GoodsService goodsService;

    @Inject
    private SimpleCategoryService cateService;

    @Inject
    private SkuDao skuDao;

    @Inject
    private SkuMoreDao skuMoreDao;

    @Inject
    private SidGenerator sidGenerator;

    @Override
    public Sku saveWithMore(final Sku s, String path) {
        final Sku[] sku = new Sku[1];

        Goods g = goodsService.find(s.getGoodsId());
        final String cateCode = g.getCateCode();

        s.setCtime(new Date());
        s.setState(Sku.STATE_ON);

        Trans.exec(new Atom() {
            public void run() {
                s.setSid(sidGenerator.nextSid(cateCode, s));
                if (null != s.getGimg()) {
                    s.setImg(s.getSid() + "." + FileUtils.getFileExtension(s.getGimg()));
                } else {
                    s.setImg(Sku.IMG_DFT);    //图片名称
                }
                sku[0] = skuDao.insertWithMore(s);
            }
        });

        if (null != s.getGimg()) {
            File pic = new File(path + File.separator + s.getImg());
            Files.copy(s.getGimg(), pic);
        }

        return sku[0];
    }

    @Override
    public Sku findWithLinks(Long id) {
        return skuDao.findWithLinks(id);
    }

    @Override
    public void updateWithMore(Sku s, String path) {
        log.debug("PATH:\n" + path);

        Sku sku = skuDao.findWithLinks(s.getId());
        if (null == sku) {
            throw new AppRuntimeException("没有找到该SKU");
        }

        sku.setModel(s.getModel());
        sku.setPprice(s.getPprice());
        sku.setSprice(s.getSprice());
        for (SkuMore sm : s.getMoreList()) {
            sm.setSkuId(sku.getId());
        }
        sku.setMoreList(s.getMoreList());
        sku.setUtime(new Date());

        if (null != s.getGimg()) {
            Files.deleteFile(new File(path + File.separator + sku.getImg()));
            File pic = new File(path + File.separator + sku.getSid() + "." + FileUtils.getFileExtension(s.getGimg()));
            Files.copy(s.getGimg(), pic);
        }
        skuDao.updateWithMore(sku);
    }

    @Override
    public void rm(Long id) {
        if (null == id || id <= 0) {
            throw new IllegalParameterException();
        }

        Sku sku = skuDao.findWithLinks(id);
        sku.setUtime(new Date());
        sku.setState(Sku.STATE_RM);
        skuDao.updateWithMore(sku);

        //REQ 现在删除时没有删除图片
        File src = new File("");
        File target = new File("");

        try {
            Files.copyFile(src, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files.deleteFile(src);
    }

    @Override
    public QueryResult list(String cateCode, String qcond, int pageNum, int pageSize) {
        if (Strings.isBlank(cateCode)) {
            throw new IllegalParameterException();
        }

        return skuDao.list(cateCode, qcond, pageNum, pageSize);
    }

    @Override
    public int reduceStock(long skuMoreId, int count) {
        SkuMore sm = skuMoreDao.find(skuMoreId);

        int currCount = sm.getCount();
        if (currCount < count) {
            throw new OutOfStockException();
        }

        sm.setCount(currCount - count);
        skuMoreDao.update(sm);

        return sm.getCount();
    }

    @Override
    public int addStock(long skuMoreId, int count) {
        if (skuMoreId <= 0 || count <= 0) {
            throw new IllegalParameterException();
        }

        SkuMore sm = skuMoreDao.find(skuMoreId);
        sm.setCount(sm.getCount() + count);
        skuMoreDao.update(sm);

        return sm.getCount();
    }

}
