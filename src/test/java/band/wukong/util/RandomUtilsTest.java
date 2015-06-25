package band.wukong.util;

import band.wukong.mz.util.RandomUtils;
import org.junit.Test;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class RandomUtilsTest {

    @Test
    public void randomCapital() {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtils.randomCapital());
        }
    }

    @Test
    public void randomCapital2() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(RandomUtils.randomCapital(new String[] {"O"}));
        }
    }

}
