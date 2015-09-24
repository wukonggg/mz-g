package band.wukong.mz.nutz.test;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class NutzTestHelper {
    private static final Log log = Logs.get();

    public static Ioc createIoc() {
        Ioc ioc = null;
        try {
            ioc = new NutIoc(new ComboIocLoader(
//                    "*org.nutz.ioc.loader.json.JsonLoader", "db.js", "beans.js",
                    "*org.nutz.ioc.loader.json.JsonLoader", "db.js",
                    "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "band.wukong.mz.g"
            ));
        } catch (ClassNotFoundException e) {
            String msg = "创建ioc容器失败";
            log.error(msg);
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return ioc;
    }

    public static void destroyIoc(Ioc ioc) {
        ioc.depose(); //关闭Ioc容器
    }
}
