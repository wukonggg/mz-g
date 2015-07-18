package band.wukong.mz.g.sale.dao;

import band.wukong.mz.g.sale.bean.Item;

import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public interface ItemDao {

    /**
     * insert
     *
     * @param item
     */
    Item insert(Item item);

    /**
     * find
     *
     * @param id
     * @return
     */
    Item find(long id);

    /**
     * update
     *
     * @param item
     */
    void update(Item item);

    /**
     *
     * @param id
     */
    Item findWithOrder(long id);

    /**
     * list items with sku info(Item.sku4Item), query by orderId
     *
     * @param orderId orderId
     * @return item list
     */
    List<Item> listWithSkuByOrder(long orderId);

    /**
     * 找出同一个order中相同的item。相同的item指的是商品属性相同，但可能同时存在购买和多个退货的item。
     *
     * @param itemId itemId
     * @return item list
     */
    List<Item> listSameItems(long itemId);
}
