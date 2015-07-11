package band.wukong.mz.util;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date工具类
 *
 * @author wukong(wukonggg@139.com)
 */
public class DateUtils {
    public static final String FMT_YYYYMMDDHHMMSS_NO_SEP = "yyyyMMddHHmmSS";
    public static final String FMT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_YYYYMMDD = "yyyy-MM-dd";
    public static final String FMT_YYYYMMDD_NO_SEP = "yyyyMMdd";

    /**
     * 按传入的日期和格式转换至字符串格式
     *
     * @param date date
     * @param type DateUtils.
     * @return
     */
    public static String format(Date date, String type) {
        if (null == date) return "";
        String strDate = "";
        SimpleDateFormat fmt = null;
        try {
            fmt = new SimpleDateFormat(type);
        } catch (Exception e) {
            e.printStackTrace();
            return strDate;
        }
        strDate = fmt.format(date);
        return strDate;
    }

    /**
     * 生成短格式日期
     *
     * @param date date
     * @return YYYY-MM-DD
     */
    public static String format(Date date) {
        if (null == date) return "";
        return format(date, FMT_YYYYMMDD);
    }

    /**
     * 返回java.sql.Date
     *
     * @param date
     * @return
     */
    public static java.sql.Date convert2SqlDate(String date) {
        if (null == date) return null;
        if (date.equalsIgnoreCase("")) return null;
        try {
            return java.sql.Date.valueOf(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 查看两个日期是否是同一天
     *
     * @param d1 d1
     * @param d2 d2
     * @return true or false
     */
    public static boolean isSameDay(Date d1, Date d2) {
        return format(d1).equals(format(d2));
    }

}
