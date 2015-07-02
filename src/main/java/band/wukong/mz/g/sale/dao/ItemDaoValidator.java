package band.wukong.mz.g.sale.dao;

import band.wukong.mz.g.sale.bean.Item;

/**
 * As you see...
 *
 * @author wukong(jinsong.sun@139.com)
 */
public class ItemDaoValidator {

    public static boolean save(Item i) {
        boolean ok = i.getId() == 0
                && i.getSkuid() > 0
                && i.getSkuMoreId() > 0
                && i.getSprice() > 0
                && i.getDprice() > 0;
        if (!ok) {
            return false;
        }

        //当state为有效时为正数，state为退货时为负数
        if (Item.STATE_OK.equals(i.getState())) {
            return i.getDcount() > 0 && i.getPayment() > 0;
        } else if (Item.STATE_RETURN.equals(i.getState())) {
            return i.getDcount() < 0 && i.getPayment() < 0;
        }
        return false;
    }
}
