package band.wukong.mz.g;

import org.nutz.ioc.impl.PropertiesProxy;

/**
 * 通过js文件被ioc容器识别
 *
 * @author wukong(wukonggg@139.com)
 */
public class AppConf extends PropertiesProxy {

    public static final String BARCODE_IMAGE_PATH = "barcode.image.path";
    public static final String BARCODE_IMAGE_WIDTH = "barcode.image.width";
    public static final String BARCODE_IMAGE_HEIGHT = "barcode.image.height";
    public static final String BARCODE_IMAGE_FORMAT = "barcode.image.format";

}
