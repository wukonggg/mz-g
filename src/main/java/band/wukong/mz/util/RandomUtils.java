package band.wukong.mz.util;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class RandomUtils {

    /**
     * 生成随机大写字母
     *
     * @return
     */
    public static String randomCapital() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return String.valueOf(chars.charAt((int) (Math.random() * 26)));
    }

    /**
     * 生成随机字母。范围A-Z。可以指定不包含的字符
     *
     * @param exceptCapitals 不包含的单个字母数组
     * @return
     */
    public static String randomCapital(String[] exceptCapitals) {
        if (null == exceptCapitals || exceptCapitals.length <= 0) {
            return "";
        }
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (String s : exceptCapitals) {
            chars = chars.replace(s, "");
        }
        return String.valueOf(chars.charAt((int) (Math.random() * (26 - exceptCapitals.length))));
    }


}
