import band.wukong.mz.g.category.bean.Category;
import band.wukong.mz.g.sale.bean.Cart;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
        int a = 1;
        System.out.println(-a);
    }


    public static void fastjson() {
        String jsonArray = "[{skuMoreId: 1}, {skuMoreId: 2}, {skuMoreId: 3}]";
        List<Cart> carts = JSON.parseArray(jsonArray, Cart.class);

        for (Cart c : carts) {
            System.out.println("c = " + c);
        }
    }

    public static void testPattern() {
        String s = "sdfl  ii 99 las    asdfsdfa";

        String[] ss = s.split("\\s+");
        for (String lin : ss) {
            System.out.println("lin = [" + lin + "]");
        }
    }

    public static void testUuid() {
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid = " + uuid);
    }

    public static void testJson() {
        List<Category> list = new ArrayList<Category>();

        Category c = new Category();
        c.setCode("code123");
        c.setOrder(123);
        c.setPcode("pcode123");
        c.setTitle("title123");

        list.add(c);
        c.setCode("code456");
        list.add(c);

        System.out.println(JSON.toJSON(list));
        System.out.println(JSONArray.toJSON(list));
    }

    public static void testNewDate() throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = fmt.parse("2015-1-1 00:10:00");
        @SuppressWarnings("unchecked")
        Date date2 = new Date("Thu Jan 01 00:10:00 CST 2015");
        System.out.println(date1.toString());
        System.out.println(date2.toString());
    }

    public static void testFile() {
        File gimg = new File("C:\\Users\\Johnson\\Desktop\\ui.jpg");
        String path = "D:\\Lab\\ws-work\\mz-g\\web\\gimg\\";
        System.out.println(gimg.getName());
    }
}
