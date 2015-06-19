package band.wukong.mz.g.sku;

import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.sku.bean.SkuMore;
import band.wukong.mz.g.sku.bean.SkuPropType;
import org.nutz.lang.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public class SkuMoreHelper {

    /**
     * 生成SkuMore的Text。因为这个SkuDict是SkuMore专用的，所以放到SkuMore的helper中
     *
     * @param sdList
     * @return
     * @deprecated 这里暂时先用固定格式的文字表示，以后再优化为小型表格
     */
    public static String convertSkuDict2Text(List<SkuPropType> sdList) {
        if (null == sdList || sdList.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (SkuPropType sd : sdList) {
            //String template = "@evalue###@cvalue\n";
            sb.append(sd.getEvalue()).append("###");
            sb.append(sd.getCvalue()).append("###");
            sb.append("0").append("###备注备注备注");
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 将文本格式的SkuMore列表数据转换成List。当save的时候使用
     *
     * @param skuMore skuMore
     * @return
     * @deprecated 这里暂时先用固定格式的文字表示，以后再优化为小型表格
     */
    public static List<SkuMore> convert2ListWhenSave(String skuMore) {
        if (Strings.isBlank(skuMore)) {
            throw new IllegalParameterException();
        }

        List<SkuMore> scList = new ArrayList<SkuMore>();

        String[] lines = skuMore.split("\r\n");
        for (String line : lines) {
            String[] more = line.split("###");
            SkuMore scm = new SkuMore();
            if (more.length == 3) {
                scm.setSize(more[0]);
                scm.setCount(Integer.parseInt(more[2]));
            } else if (more.length == 4) {
                scm.setSize(more[0]);
                scm.setCount(Integer.parseInt(more[2]));
                scm.setRemark(more[3]);
            } else {
                throw new AppRuntimeException("Illegal data was found. Please check your input content.");
            }
            scList.add(scm);
        }
        return scList;

    }

    /**
     * 将文本格式的SkuMore列表数据转换成List。当update的时候使用
     *
     * @param skuMore skuMore
     * @return
     * @deprecated 这里暂时先用固定格式的文字表示，以后再优化为小型表格
     */
    public static List<SkuMore> convert2ListWhenUpdate(String skuMore) {
        if (Strings.isBlank(skuMore)) {
            throw new IllegalParameterException();
        }

        List<SkuMore> scList = new ArrayList<SkuMore>();

        String[] lines = skuMore.split("\r\n");
        for (String line : lines) {
            String[] more = line.split("###");
            SkuMore scm = new SkuMore();
            if (more.length == 2) {
                scm.setSize(more[0]);
                scm.setCount(Integer.parseInt(more[1]));
            } else if (more.length == 3) {
                scm.setSize(more[0]);
                scm.setCount(Integer.parseInt(more[1]));
                scm.setRemark(more[2]);
            } else {
                throw new AppRuntimeException("Illegal data was found. Please check your input content.");
            }
            scList.add(scm);
        }
        return scList;

    }

    /**
     * 将List<SkuClothingMore>转换成html页面的textarea使用的文本
     *
     * @param scmList
     * @return
     * @deprecated 这里暂时先用固定格式的文字表示，以后再优化为小型表格
     */
    public static String convert2Text(List<SkuMore> scmList) {
        if (null == scmList || scmList.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (SkuMore sd : scmList) {
            sb.append(sd.getSize());
            sb.append("###").append(sd.getCount());
            if (Strings.isNotBlank(sd.getRemark())) {
                sb.append("###").append(sd.getRemark());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
