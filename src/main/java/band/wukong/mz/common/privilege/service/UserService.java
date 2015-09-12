package band.wukong.mz.common.privilege.service;

import band.wukong.mz.common.privilege.bean.User;
import band.wukong.mz.common.privilege.exception.UnAuthorizedException;
import band.wukong.mz.g.AppConst;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.service.IdNameEntityService;

import java.util.Date;
import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "userService", fields = "dao")
public class UserService extends IdNameEntityService<User> {
    /**
     * login
     *
     * @param name name
     * @param password password
     * @return id of user. return -1 if invalid.
     */
    public User login(String name, String password) throws UnAuthorizedException {
        User user = fetch(name);
        if (user == null) {
            throw new UnAuthorizedException("用户名或密码不正确");
        }

        String _pass = new Sha256Hash(password, user.getSalt()).toHex();
        if(!_pass.equalsIgnoreCase(user.getPassword())) {
            throw new UnAuthorizedException("用户名或密码不正确");
        }
        return user;
    }

    public void updatePassword(long userId, String password) {
        User user = fetch(userId);
        if (user == null) {
            return;
        }
        user.setSalt(R.UU16());
        user.setPassword(new Sha256Hash(password, user.getSalt()).toHex());
        user.setUpdateTime(new Date());
        dao().update(user, "^(password|salt|updateTime)$");
//        dao.updateIgnoreNull(user);
    }

    public User save(String name, String password) {
        User user = new User();
        user.setName(name.trim());
        user.setSalt(R.UU16());
        user.setPassword(new Sha256Hash(password, user.getSalt()).toHex());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setState(User.STATE_OK);
        return dao().insert(user);
    }

    public void rm(long userId) {
        User user = fetch(userId);
        if (user == null) {
            return;
        }
        user.setState(User.STATE_RM);
        user.setUpdateTime(new Date());
        dao().update(user);
    }

    public QueryResult list(String name, Pager pager) {
        Cnd cnd = Strings.isBlank(name)? null : Cnd.where("name", "like", "%"+name+"%");
        List<User> list = dao().query(User.class, cnd, pager);
        int count = dao().count(User.class, cnd);

        QueryResult qr = new QueryResult();
        qr.setList(list);
        pager.setRecordCount(count);
        pager.setPageSize(AppConst.PAGE_SIZE);
        qr.setPager(pager);

        return qr;
    }
}
