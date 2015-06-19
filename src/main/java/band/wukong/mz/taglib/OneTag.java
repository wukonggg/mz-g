package band.wukong.mz.taglib;

import org.nutz.lang.Strings;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class OneTag {

    /**
     * tostring with check of "null or empty"
     *
     * @param o object
     * @return String
     */
    public static String objectToString(Object o) {
        return null != o ? o.toString() : "";

    }

    /**
     * dateToStringShort
     *
     * @param date date
     * @return yyyy-MM-dd
     */
    public static String dateToStringShort(Date date) {
        if (null == date) return "";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(date);
    }

    /**
     * dateToStringLong
     *
     * @param date date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStringLong(Date date) {
        if (null == date) return "";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(date);
    }

    /**
     * 将给定字符串，超出截取字符的个数，就变成 "xxx..." 形式的字符串
     *
     * @param s   原始字符串
     * @param len 截取字符个数
     * @return 缩短的字符串
     */
    public static String string4short(String s, int len) {
        if (Strings.isBlank(s) || len <= 0 || s.length() <= len) {
            return s;
        }
        return s.substring(0, len) + "...";
    }

    public static boolean isZero(int i) {
        return i == 0;
    }

}
