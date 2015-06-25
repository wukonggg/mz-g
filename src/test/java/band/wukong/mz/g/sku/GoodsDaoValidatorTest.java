package band.wukong.mz.g.sku;

import band.wukong.mz.g.sku.bean.Goods;
import band.wukong.mz.g.sku.dao.GoodsDaoValidator;
import org.junit.Assert;
import org.junit.Test;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class GoodsDaoValidatorTest {

    @Test
    public void testCheckInsert() {
        Goods g1 = new Goods();
        g1.setCateCode("MILK");
        g1.setGname("Oldenburg Milk");
        g1.setImg("oldenburg_milk.png");
        Assert.assertTrue(GoodsDaoValidator.insert(g1));


        Goods g2 = new Goods();
        g2.setCateCode("MILK");
        g2.setGname("Mengniu Milk");
        g2.setImg("mengniu_milk.png");
        Assert.assertFalse(GoodsDaoValidator.insert(g2));
    }
}
