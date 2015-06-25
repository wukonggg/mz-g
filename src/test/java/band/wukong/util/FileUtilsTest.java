package band.wukong.util;

import band.wukong.mz.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class FileUtilsTest {

    @Test
    public void testGetFileExtension() {
        File gimg = new File("C:\\Users\\Johnson\\Desktop\\a.jpg");
        String ext = FileUtils.getFileExtension(gimg);
        Assert.assertEquals("出错啦！", "jpg", ext);
    }

    @Test
    public void testGetFileNameWithoutExtension() {
        File file = new File("C:\\Users\\Johnson\\Desktop\\a.jpg");
        String name = FileUtils.getFileNameWithoutExtension(file.getName());
        Assert.assertEquals("出错啦！", "a", name);
    }
}
