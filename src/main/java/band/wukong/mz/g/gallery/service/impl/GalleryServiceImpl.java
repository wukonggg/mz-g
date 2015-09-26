package band.wukong.mz.g.gallery.service.impl;

import band.wukong.mz.g.gallery.service.GalleryServive;
import band.wukong.mz.g.sku.bean.Goods;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "galleryServive")
public class GalleryServiceImpl implements GalleryServive {

    @Inject
    private Dao dao;

    @Override
    public List<Goods> search(String kw) {
        Condition c = Cnd.where("gname", "like", "%" + kw + "%").or("kw", "like", "%" + kw + "%");

        return dao.query(Goods.class, c);
    }
}
