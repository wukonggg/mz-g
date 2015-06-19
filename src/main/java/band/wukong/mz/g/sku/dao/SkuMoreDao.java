package band.wukong.mz.g.sku.dao;

import band.wukong.mz.g.sku.bean.SkuMore;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public interface SkuMoreDao {

    /**
     * find
     *
     * @param id id
     */
    SkuMore find(long id);


    /**
     * update
     *
     * @param sm sm
     * @return
     */
    void update(SkuMore sm);

}
