package band.wukong.util;

import band.wukong.mz.util.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class NumberUtilsTest {

    @Test
    public void length() {
        Assert.assertEquals(1, NumberUtils.length(9));
        Assert.assertEquals(2, NumberUtils.length(99));
        Assert.assertEquals(3, NumberUtils.length(999));
        Assert.assertEquals(6, NumberUtils.length(999999));
    }
}
