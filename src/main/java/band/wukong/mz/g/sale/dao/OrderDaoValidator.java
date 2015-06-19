package band.wukong.mz.g.sale.dao;

import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.bean.Order;

import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class OrderDaoValidator {

    public static boolean save(Order o) {
        boolean ready = null != o
                && null != o.getItems()
                && o.getItems().size() > 0
                && o.getId() == 0
                && o.getCustId() > 0
                && null != o.getDtime();

        if (!ready) {
            return false;
        }

        List<Item> items = o.getItems();
        for (Item i : items) {
            if (i.getId() != 0) {
                return false;
            }
            if (!ItemValidator.save(i)) {
                return false;
            }
        }

        return true;
    }

    private static class ItemValidator {

        /**
         * inner validator for OrderServiceValidator.save(Order o)
         *
         * @param i
         * @return
         */
        public static boolean save(Item i) {
            return i.getId() == 0
                    && i.getSkuid() > 0
                    && i.getSkuMoreId() > 0
                    && i.getDprice() > 0
                    && i.getOid() == 0;
        }
    }
}


