package band.wukong.mz.g.category.dao;

import band.wukong.mz.g.category.bean.Category;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "cateDao")
public class CategoryDao {

    @Inject
    private Dao dao;

    /**
     * 根据父编码查询其下所有子编码
     *
     * @param pcode
     * @return
     */
    public List<Category> list(String pcode) {
        if (null == pcode || "".equals(pcode)) {
            throw new RuntimeException("Invalid param was found during pre-list check.");
        }
        return dao.query(Category.class,
                Cnd.where("pcode", "=", pcode).and("state", "=", Category.STATE_OK).orderBy("ord", "asc"));
    }

}
