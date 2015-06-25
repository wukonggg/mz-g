package band.wukong.mz.g.sale.servcie;

import band.wukong.mz.g.sale.service.DiscountRule;
import org.junit.Test;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class DiscountRuleTest {

    @Test
    public void discount() {
        System.out.println("DiscountRule.discount(\"S-1\", 529.00) = " + DiscountRule.discount("S-1", 529.20));
    }
}
