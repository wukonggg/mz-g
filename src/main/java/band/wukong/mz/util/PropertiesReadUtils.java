package band.wukong.mz.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class PropertiesReadUtils {
    private static FileInputStream fis;
    private static Properties props;

    /**
     * 根据key读取value
     *
     * @param fileName 比如："filename.properties"
     * @param key      比如："app.mode"
     * @return
     */
    public static String readValue(String fileName, String key) {
        if (null == fis) {
            load(fileName);
        }
        try {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Properties file can not be read.");
        }
        return props.getProperty(key);
    }

    /**
     * 根据key读取value
     *
     * @param key 比如："app.mode"
     * @return
     */
    public static String readValue(String key) {
        if (null == fis) {
            throw new RuntimeException("FileInputStream of properties file is null");
        }
        try {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Properties file can not be read.");
        }
        return props.getProperty(key);
    }

    private static void load(String fileName) {
        try {
            fis = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}