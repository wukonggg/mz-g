package band.wukong.mz.g.gallery.service;

import band.wukong.mz.g.sku.bean.Goods;

import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public interface GalleryServive {

    /**
     * 检索
     *
     * @param kw 关键字
     * @return
     */
    List<Goods> search(String kw);
}
