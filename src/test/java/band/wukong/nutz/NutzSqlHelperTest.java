package band.wukong.nutz;

import band.wukong.mz.nutz.NutzSqlHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class NutzSqlHelperTest {

    @Test
    public void convertSql2Count() {
        String s1 =
            "select t1.id as 'goods_id', t1.cate_code, t1.gname\n" +
            "     , t.id as 'sku_clothing_id', t.model, t.img, t.pprice\n" +
            "from t_sku_clothing t\n" +
            "inner join t_goods t1 on t1.id = t.goods_id\n" +
            "where t.state = @t_state and t1.state = @t1_state\n" +
            "and t1.cate_code = @cate_code\n" +
            "and (t.sid like @qcond or t1.gname like @qcond)";

        String s2 =
            "select count(*)\n" +
            "from t_sku_clothing t\n" +
            "inner join t_goods t1 on t1.id = t.goods_id\n" +
            "where t.state = @t_state and t1.state = @t1_state\n" +
            "and t1.cate_code = @cate_code\n" +
            "and (t.sid like @qcond or t1.gname like @qcond)";

        String s = NutzSqlHelper.convertSql2Count(s1);
        Assert.assertEquals(s2, s);
    }
}
