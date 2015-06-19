package band.wukong.mz.g.privilege.service.impl;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.privilege.bean.User;
import band.wukong.mz.g.privilege.service.UserService;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "userService")
public class UserServiceImpl implements UserService {

    private static final Log log = Logs.get();

    @Override
    public User login(User u) {
        if (null == u) {
            throw new IllegalParameterException();
        }

        boolean ok = "Yolanda".equals(u.getLoginName()) && "iwukong".equals(u.getPwd());

        if (ok) {
            return genValidUser();
        } else {
            log.error("Access system denied.");
            throw new SecurityException("你妈喊你回家吃饭！");
        }

    }

    @Override
    public User find(long userId) {
        if (userId != 1L) {
            return null;
        }
        return genValidUser();
    }


    private User genValidUser() {
        User user = new User();
        user.setId(1L);
        user.setLoginName("Yolanda");
        user.setStat(1);
        return user;
    }
}
