package band.wukong.mz.g.sku.service;

import band.wukong.mz.g.sku.bean.Sku;
import org.nutz.dao.QueryResult;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public interface SkuService {

    /**
     * 保存Sku以及SkuMore。先保存图片文件到文件系统，再进数据库。
     * 检查moreList中是否有数量为0的，如果有，就remove掉。
     *
     * @param s    pojo without id
     * @param path 图片文件存放路径
     */
    Sku saveWithMore(Sku s, String path);

    /**
     * 找某个保存Sku，包括more,goods
     *
     * @param id id
     * @return
     */
    Sku findWithLinks(long id);

    /**
     * update db and file system。注意：调用者必须保证morelist不为null
     * 检查moreList中是否有数量为0的，如果有，就remove掉。
     * 判断count，如果sku的newCount>0，触发自动上架。如果sku的newCount=0 && goods的currCount=0 触发自动下架。
     *
     * @param sc 包含moreList
     * @param path     图片文件存放路径
     */
    void updateWithMore(Sku sc, String path);

    /**
     * remove in db。已经有order的sku不能删除，如果遇到，会抛出SkuInOrderException
     *
     * @param id id
     */
    void rm(Long id);

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

    /**
     * （下单时）减少库存数量
     *
     * @param skuMoreId skuMoreId
     * @param count     需要减少的库存量
     * @return 返回剩余库存量
     */
    int reduceStock(long skuMoreId, int count);

    /**
     * （退货时）增加库存量。在退货时如果发现当前sku的总库存为0，触发自动自动上架。
     *
     * @param skuMoreId skuMoreId
     * @param count     需要增加的库存量
     * @return 返回当前总库存量
     */
    int addStock(long skuMoreId, int count);

    /**
     * sku及商品下架
     * @param goodsId
     */
    void offShelf(long goodsId);
}
