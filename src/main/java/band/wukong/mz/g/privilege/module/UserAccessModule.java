package band.wukong.mz.g.privilege.module;

import band.wukong.mz.g.privilege.bean.User;
import band.wukong.mz.g.privilege.service.UserService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpSession;

/**
 * @author wukong(wukonggg@139.com)
 */
@IocBean
public class UserAccessModule {
    private static final Log log = Logs.get();

    @Inject
    private UserService userService;

    @At("/entry")
    @Ok("jsp:view.entry")
    public void entry() {
    }

    @At("/login")
    @Ok("redirect:/main.io")
    @Fail("redirect:/entry.io")
    public void login(@Param("..") User u, HttpSession session) {
        if (null != session.getAttribute("me")) {
            return;
        }

        User user = userService.login(u);
        session.setAttribute("me", user);
    }

    @At("/main")
    @Filters(@By(type = CheckSession.class, args = {"me", "/entry.io"}))
    @Ok("jsp:view.main")
    @Fail("redirect:/entry.io")
    public void main() {
    }


    @At("/logout")
    @Ok("redirect:/entry.io")
    public void logout(HttpSession session) {
        session.setAttribute("me", null);
    }

}
