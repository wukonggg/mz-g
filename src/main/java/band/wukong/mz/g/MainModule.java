package band.wukong.mz.g;

import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * Main Moudule
 *
 * @author wukong(wukonggg@139.com)
 */
@Encoding(input = "UTF-8", output = "UTF-8")
@Fail("jsp:view.base.error")
@IocBy(type = ComboIocProvider.class, args = {
//        "*org.nutz.ioc.loader.json.JsonLoader", "ioc/db.js", "ioc/beans.js",
        "*org.nutz.ioc.loader.json.JsonLoader", "ioc/",
        "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "band.wukong.mz"
})
@Modules(scanPackage = true)
@SetupBy(value = MainSetup.class)
public class MainModule {
    /*
        另一种ioc：  http://nutzbook.wendal.net/setup30mins/configure_iocby.html
        @IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
                                           "*anno", "net.wendal.nutzbook",
                                           "*tx"})

     */
}
