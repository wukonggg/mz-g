package band.wukong.util;

import band.wukong.mz.util.Calculator;
import org.junit.Assert;
import org.junit.Test;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class CalculatorTest {

    @Test
    public void add() {
        Assert.assertTrue(Calculator.add(82.89, 0.03) == 82.92);
        Assert.assertTrue(Calculator.add(82.89, 1.03) == 83.92);
        Assert.assertTrue(Calculator.add(82.89, 1.22) == 84.11);
    }

    @Test
    public void sub() {
        Assert.assertTrue(Calculator.sub(82.89, 0.03) == 82.86);
        Assert.assertTrue(Calculator.sub(82.89, 1.03) == 81.86);
        Assert.assertTrue(Calculator.sub(82.89, 1.22) == 81.67);
        Assert.assertTrue(Calculator.sub(82.89, 3.89) == 79);
    }

    @Test
    public void mul() {
        Assert.assertTrue(Calculator.mul(100, 1) == 100);
        Assert.assertTrue(Calculator.mul(100, 2) == 200);
        Assert.assertTrue(Calculator.mul(123, 3) == 369);
    }

    @Test
    public void div() {
        Assert.assertTrue(Calculator.div(100, 1) == 100);
        Assert.assertTrue(Calculator.div(100, 2) == 50);
        Assert.assertTrue(Calculator.div(333, 3) == 111);
        System.out.println(Calculator.div(345, 56562));
        System.out.println(Calculator.div(399, 2));
    }

    @Test
    public void round() {
        Assert.assertTrue(90 == Calculator.round(89.806, 0));
        Assert.assertTrue(89.8 == Calculator.round(89.806, 1));
        Assert.assertTrue(89.81 == Calculator.round(89.806, 2));

    }
}
