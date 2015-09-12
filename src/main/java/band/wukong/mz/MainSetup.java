package band.wukong.mz;

import band.wukong.mz.common.privilege.bean.User;
import band.wukong.mz.g.AppWebConst;
import band.wukong.mz.g.category.bean.Category;
import band.wukong.mz.g.category.service.SimpleCategoryService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.lang.random.R;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import javax.servlet.ServletContext;
import java.util.Date;
import java.util.Map;

/**
 * Main Setup
 *
 * @author wukong(wukonggg@139.com)
 */
public class MainSetup implements Setup {
    private static final Log log = Logs.get();

    @Override
    public void init(NutConfig nc) {
        log.info("MainSetup init...");

        Ioc ioc = nc.getIoc();

        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "band.wukong.mz", false);
        createAdminIfNoneUser(dao);

        loadCategory(ioc.get(SimpleCategoryService.class), nc.getServletContext());
    }

    private void createAdminIfNoneUser(Dao dao) {
        if (dao.count(User.class) == 0) {
            User user = new User();
            user.setName("admin");
            user.setPassword(new Sha256Hash("123456", user.getSalt()).toHex());
            user.setSalt(R.UU16());
            user.setCreateTime(new Date());
            user.setUpdateTime(user.getCreateTime());
            user.setState(User.STATE_OK);
            dao.insert(user);
        }
    }

    @Override
    public void destroy(NutConfig nc) {
        log.info("MainSetup destroy...");
    }

    /**
     * 载入category
     *
     * @param cateService
     * @param context
     */
    private void loadCategory(SimpleCategoryService cateService, ServletContext context) {
        Map<String, Category> cateMap = cateService.listAllFlat();
        context.setAttribute(AppWebConst.APP_CATE_FLAT_MAP, cateMap);
    }
}
