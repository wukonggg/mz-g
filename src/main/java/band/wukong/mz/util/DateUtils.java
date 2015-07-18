package band.wukong.mz.util;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */

import java.text.ParseException;
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
     * String转化Date。
     *
     * @param date 支持的格式：yyyy-MM-dd, yyyy-MM-dd HH:mm:ss
     *
     * @return java.util.Date
     */
    public static Date convert2date(String date) {
        //RegexUtil.P_DATE_YYYYMMDD
        String re_yymmdd = "^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]|[0-9][1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$";
        if (date.matches(re_yymmdd)) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        //RegexUtil.P_DATE_YYYYMMDD_HHMMSS
        String re_yymmdd_hhmmss = "^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]|[0-9][1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))\\p{Space}[0-2][0-9]:[0-5][0-9]:[0-5][0-9]$";
        if (date.matches(re_yymmdd_hhmmss)) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException();
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
