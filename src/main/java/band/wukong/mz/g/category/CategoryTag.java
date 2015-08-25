package band.wukong.mz.g.category;

import band.wukong.mz.g.category.bean.Category;

import java.util.Map;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class CategoryTag {

    /**
     * 根据code获取title
     *
     * @param code    code
     * @param cateMap category map
     * @return string
     */
    public static String title(String code, Map<String, Category> cateMap) {
        Category cate = cateMap.get(code);
        return null != cate ? cate.getTitle() : "未知编码：" + code;
    }

}
