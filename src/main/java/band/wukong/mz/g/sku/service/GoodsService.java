package band.wukong.mz.g.sku.service;

import band.wukong.mz.g.sku.bean.Goods;
import org.nutz.dao.QueryResult;

/**
 * Goods Servcie
 *
 * @author wukong(wukonggg@139.com)
 */
public interface GoodsService {

    /**
     * 保存商品信息到数据库，并保存图片文件到文件系统
     *
     * @param g    pojo without id
     * @param path 图片文件存放路径
     */
    Goods save(Goods g, String path);

    /**
     * 找某个商品
     *
     * @param id id
     * @return
     */
    Goods find(long id);

    /**
     * 找某个商品
     *
     * @param gname gname
     * @return
     */
    Goods find(String gname);

    /**
     * update db and file system. cateCode will not be udpated.
     *
     * @param g
     * @param path 图片文件存放路径
     */
    void update(Goods g, String path);

    /**
     * remove in db
     *
     * @param id id
     */
    void rm(long id);

    /**
     * 查询
     *
     * @param cateCode cateCode
     * @param qcond    qcond
     * @param pageNum  page number
     * @param pageSize page size
     * @return QueryResult 包含GoodsList和Page
     */
    QueryResult list(String cateCode, String qcond, int pageNum, int pageSize);

    QueryResult listNoneSku(String qcond, int pageNum, int pageSize);
}
