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
                && Strings.isNotBlank(c.getCid())
                && Strings.isNotBlank(c.getMsisdn());
    }

    public static boolean update(Customer c) {
        return null != c
                && c.getId() > 0
                && Strings.isNotBlank(c.getCid())
                && Strings.isNotBlank(c.getMsisdn());
    }
}
