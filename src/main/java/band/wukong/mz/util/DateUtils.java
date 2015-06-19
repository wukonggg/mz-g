package band.wukong.mz.util;

/**
 * description
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
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmSS";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS_WITH_SEP = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YYYYMMDD_WITH_SEP = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YYYYMMDD_WITHOUT_SEP = "yyyyMMdd";

    /**
     * 按传入的日期和格式转换至字符串格式
     *
     * @param date
     * @param type
     * @return
     */
    public static String getStringFormat(Date date, String type) {
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
     * 返回java.sql.Date
     *
     * @param date
     * @return
     */
    public static java.sql.Date getSqlDate(String date) {
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
     * 生成短格式日期
     *
     * @param date
     * @return 2008-09-17
     */
    public static String genDate(Date date) {
        if (null == date) return "";
        return getStringFormat(date, DATE_FORMAT_YYYYMMDD_WITH_SEP);
    }

    /**
     * 查看两个日期是否是同一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDay(Date d1, Date d2) {
        return genDate(d1).equals(genDate(d2));
    }

}

