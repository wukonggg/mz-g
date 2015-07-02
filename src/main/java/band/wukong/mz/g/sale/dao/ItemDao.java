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
     * list by order
     *
     * @param orderId orderId
     * @return item list
     */
    List<Item> listWithSkuByOrder(long orderId);

    /**
     * 找出（同一个order中）相同的item
     *
     * @param itemId
     * @return
     */
    List<Item> listSameItems(long itemId);
}
