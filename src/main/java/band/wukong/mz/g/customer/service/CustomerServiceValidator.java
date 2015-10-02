package band.wukong.mz.g.customer.service;

import band.wukong.mz.g.customer.bean.Customer;
import org.nutz.lang.Strings;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class CustomerServiceValidator {

    public static boolean save(Customer c) {
        return null != c
                && c.getId() == 0
                && Strings.isNotBlank(c.getCid());
    }

    public static boolean update(Customer c) {
        return null != c
                && c.getId() > 0
                && Strings.isNotBlank(c.getCid());
    }

    public static boolean updatePayment(Customer c) {
        return null != c
                && c.getId() > 0
                && c.getPaymentClothing() >= 0;
    }
}
