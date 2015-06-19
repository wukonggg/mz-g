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
        "*org.nutz.ioc.loader.json.JsonLoader", "db.js", "beans.js",
        "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "band.wukong.mz.g"
})
@Modules(scanPackage = true)
@SetupBy(value = MainSetup.class)
public class MainModule {
}
