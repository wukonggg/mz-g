package band.wukong.mz.nutz;

/**
 * org.nutz.dao.sql 工具类
 *
 * @author wukong(wukonggg@139.com)
 */
public class NutzSqlHelper {

    /**
     * 将一个select查询sql的select内容变成count(*)。适用于mysql
     *
     * @param sql 原始sql
     * @return select count(*) as 'count' from ....
     */
    public static String convertSql2Count(String sql) {
        String count = "select count(*)\n";
        int index = sql.indexOf("from");
        return count + sql.substring(index);
    }

    /**
     * 将一个select查询sql的select内容变成count(*)。适用于mysql
     *
     * @param sql 原始sql
     * @return select count(*) as 'count' from ....
     */
    public static String convertSql2Count2(String sql) {
        String count = "select count(*) from (?) z\n";
        return count.replace("?", sql);
    }
}
