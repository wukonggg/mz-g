package band.wukong.mz.g.category.service;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.category.bean.Category;
import band.wukong.mz.g.category.dao.CategoryDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "cateService")
public class SimpleCategoryService {
    private static final Log log = Logs.get();

    @Inject("refer:cateDao")
    private CategoryDao cateDao;

    /**
     * 根据父编码查询其下所有子编码，如果没有父编码为null或者为空，就以根编码为父编码
     *
     * @param pcode 父编码
     * @return List<Category>
     */
    public List<Category> list(String pcode) {
        if (null == pcode || "".equals(pcode)) {
            throw new IllegalParameterException();
        }
        return cateDao.list(pcode);
    }

    /**
     * 查询所有节点，平行展示。
     *
     * @return Map<String, Category>
     */
    public Map<String, Category> listAllFlat() {
        List<Category> cateList = cateDao.list(Category.CATE_CODE_TYPE_SIMPLE);
        ;

        Map<String, Category> cateMap = new HashMap<String, Category>();
        for (Category cate : cateList) {
            cateMap.put(cate.getCode(), cate);
        }

        return cateMap;
    }

}
