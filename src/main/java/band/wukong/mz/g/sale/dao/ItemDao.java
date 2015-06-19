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
     * save
     *
     * @param item
     */
    void save(Item item);

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
}
