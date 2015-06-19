package band.wukong.mz.util;

import java.io.File;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class FileUtils {

    /**
     * 获取文件扩展名
     *
     * @param file
     * @return
     */
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    /**
     * 获取文件名称，不含扩展名
     *
     * @param fileName
     * @return
     */
    public static String getFileNameWithoutExtension(String fileName) {
        if (null == fileName || "".equals(fileName)) {
            throw new RuntimeException("文件名不合法");
        }

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        } else {
            return "";
        }
    }
}
