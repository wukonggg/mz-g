package band.wukong.mz.g.sku.service.impl;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.dao.GoodsDao;
import band.wukong.mz.g.sku.service.GoodsService;
import band.wukong.mz.util.FileUtils;
import org.nutz.dao.QueryResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "goodsService")
public class GoodsServiceImpl implements GoodsService {
    private static final Log log = Logs.get();

    @Inject
    private GoodsDao goodsDao;

    @Override
    public Goods save(Goods g, String path) {
        if (null != g.getGimg()) {
            File pic = new File(path + File.separator + UUID.randomUUID() + "." + FileUtils.getFileExtension(g.getGimg()));
            Files.copy(g.getGimg(), pic);
            g.setImg(pic.getName());    //图片名称
        } else {
            g.setImg(Goods.IMG_DFT);    //图片名称
        }
        g.setState(Goods.STATE_OK);
        return goodsDao.insert(g);
    }

    @Override
    public Goods find(long id) {
        return goodsDao.find(id);
    }

    @Override
    public Goods find(String gname) {
        return goodsDao.find(gname);
    }

    @Override
    public void update(Goods g, String path) {
        log.debug("PATH:\n" + path);

        if (null != g.getGimg()) {
            File pic = new File(path + File.separator + UUID.randomUUID() + "." + FileUtils.getFileExtension(g.getGimg()));
            Files.copy(g.getGimg(), pic);
            g.setImg(pic.getName());
        }
        Goods goods = goodsDao.find(g.getId());
        goods.setGname(g.getGname());
        goods.setImg(g.getImg());
        goods.setWords(g.getWords());
        goods.setUtime(new Date());
        goodsDao.update(goods);
    }

    @Override
    public void rm(long id) {
        if (id <= 0) {
            throw new IllegalParameterException();
        }

        Goods g = goodsDao.find(id);
        g.setState(Goods.STATE_RM);
        goodsDao.update(g);
        // REQ 补充业务逻辑：当删除商品时，商品如果有对应SKU，则提示不能被删除
        // REQ 现在删除时没有删除图片
    }

    @Override
    public QueryResult list(String cateCode, String qcond, int pageNum, int pageSize) {
        return goodsDao.list(cateCode, qcond, pageNum, pageSize);
    }

    @Override
    public QueryResult listNoneSku(String qcond, int pageNum, int pageSize) {
        return goodsDao.listWitchHasNoneSku(qcond, pageNum, pageSize);
    }
}
