package band.wukong.mz.g.sku;

import band.wukong.mz.g.sku.SkuMoreHelper;
import band.wukong.mz.g.sku.bean.SkuMore;
import com.alibaba.fastjson.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class SkuMoreHelperTest {

    @Test
    public void convert2Text() {
        List<SkuMore> scmList = new ArrayList<SkuMore>();
        SkuMore scm1 = new SkuMore();
        scm1.setSize("S");  scm1.setCount(3);
        SkuMore scm2 = new SkuMore();
        scm2.setSize("M");  scm2.setCount(3); scm2.setRemark("HOHOHO");
        SkuMore scm3 = new SkuMore();
        scm3.setSize("L");  scm3.setCount(3); scm3.setRemark("HIHIHI");
        scmList.add(scm1);
        scmList.add(scm2);
        scmList.add(scm3);

        String text = SkuMoreHelper.convert2Text(scmList);
        Assert.assertNotNull(text);
        System.out.println(text);
    }



    @Test
    public void convert2ListWhenSave() {
        String text =
            "S###小号###\r\n" +
                "M###中号###哈哈哈哈\r\n" +
                "L###大号###\r\n" +
                "XL###特大号###\r\n" +
                "XXL###超大号###";
        List<SkuMore> scmList = SkuMoreHelper.convert2ListWhenSave(text);
        System.out.println(JSONArray.toJSONString(scmList));
        Assert.assertEquals(5, scmList.size());

    }

}
