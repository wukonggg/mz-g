package band.wukong.taglib;

import band.wukong.mz.taglib.OneTag;
import org.junit.Assert;
import org.junit.Test;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class OneTagTest {

    @Test
    public void string4short() {
        String s0 = "";
        Assert.assertEquals("", OneTag.string4short(s0, 0));
        Assert.assertEquals("", OneTag.string4short(s0, 9));


        String s1 = "o";
        Assert.assertEquals("o", OneTag.string4short(s1, -1));
        Assert.assertEquals("o", OneTag.string4short(s1, 0));
        Assert.assertEquals("o", OneTag.string4short(s1, 1));
        Assert.assertEquals("o", OneTag.string4short(s1, 2));
        Assert.assertEquals("o", OneTag.string4short(s1, 3));

        String s2 = "hi";
        Assert.assertEquals("hi", OneTag.string4short(s2, 0));
        Assert.assertEquals("h...", OneTag.string4short(s2, 1));
        Assert.assertEquals("hi", OneTag.string4short(s2, 3));
        Assert.assertEquals("hi", OneTag.string4short(s2, 4));
        Assert.assertEquals("hi", OneTag.string4short(s2, 4));


        String ss = "one night in beijing";
        Assert.assertEquals("one n...", OneTag.string4short(ss, 5));
    }

}
