package band.wukong.mz.g.sku.dao;

import band.wukong.mz.g.sku.bean.Goods;
import org.nutz.dao.QueryResult;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public interface GoodsDao {
    /**
     * insert into db.insert时不改变state属性。
     *
     * @param g pojo without id
     */
    Goods insert(Goods g);

    /**
     * find from db
     *
     * @param id id
     * @return
     */
    Goods find(Long id);


    /**
     * update
     *
     * @param g
     */
    void update(Goods g);

    /**
     * remove in db
     *
     * @param id id
     */
    void rm(Long id);

    /**
     * query from db
     *
     * @param cateCode cateCode
     * @param qcond    qcond
     * @param pageNum  page number
     * @param pageSize page size
     * @return QueryResult 包含GoodsList和Page
     */
    QueryResult list(String cateCode, String qcond, int pageNum, int pageSize);

    /**
     * query from db
     *
     * @param qcond    qcond
     * @param pageNum  page number
     * @param pageSize page size
     * @return QueryResult 包含GoodsList和Page
     */
    QueryResult listWitchHasNoneSku(String qcond, int pageNum, int pageSize);
}
