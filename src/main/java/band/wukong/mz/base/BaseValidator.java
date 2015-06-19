package band.wukong.mz.base;

import java.util.Date;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class BaseValidator {

    public static boolean notNull(Date dt) {
        return null != dt;
    }

    public static boolean gtZero(Integer num) {
        return null != num && num > 0;
    }

    public static boolean gtZero(Long num) {
        return null != num && num > 0;
    }

}
