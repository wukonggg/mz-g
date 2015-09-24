package band.wukong.mz.common.privilege.module;

import band.wukong.mz.common.privilege.bean.User;
import band.wukong.mz.common.privilege.exception.UnAuthorizedException;
import band.wukong.mz.common.privilege.service.UserService;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpSession;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean
@Filters(@By(type = CheckSession.class, args = {"me", "/"}))
@At
public class UserModule {

    private static final Log log = Logs.get();

    @Inject
    private UserService userService;

    @At(value = "/entry", top = true)
    @Filters() // 覆盖UserModule类的@Filter设置,因为登陆可不能要求是个已经登陆的Session
    @Ok("jsp:view.entry")
    public void entry() {
    }

    @At(top = true)
    @Filters() // 覆盖UserModule类的@Filter设置,因为登陆可不能要求是个已经登陆的Session
    @POST
    @Ok("json")
    @Fail("json")
    public Object login(@Param("name") String name, @Param("password") String password, HttpSession session) {
        log.debug("Input params - name: \n" + name);
        log.debug("Input params - password: \n" + password);

        NutMap re = new NutMap();

        User user;
        try {
            user = userService.login(name, password);
        } catch (UnAuthorizedException e) {
            return re.setv("ok", false).setv("msg", e.getMessage());
        }

        session.setAttribute("me", user);
        return re.setv("ok", true);
    }

    @At(top = true)
    @Ok(">>:/") // 跟其他方法不同,这个方法完成后就跳转首页了
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @At(top = true)
    @Ok("jsp:view.main")
    public void main() {
    }

    @At
    @Ok("jsp:view.common.privilege.user_list")
    public void index() {
    }

    @At("/add")
    @Ok("jsp:view.common.privilege.user_add")
    public void add() {
    }

    @At("/save")
    @Ok("redirect:/user/list.io")
//    @Ok("json:{locked:'password|salt',ignoreNull:true}") // 忽略password和salt属性,忽略空属性的json输出
    public Object save(@Param("..") User u) {
        NutMap re = new NutMap();

        String msg = checkUser(u, true);
        if (msg != null) {
            return re.setv("ok", false).setv("msg", msg);
        }

        User user = userService.save(u.getName(), u.getPassword());
        return re.setv("ok", true).setv("data", user);
    }

    @At("/mod_passwd")
    @Ok("jsp:view.common.privilege.user_mod")
    public void mod_passwd() {
    }

    @At("/upd_passwd")
    @Ok("redirect:/user/list.io")
    public Object upd_passwd(@Param("..") User user) {
        NutMap re = new NutMap();
        String msg = checkUser(user, false);
        if (msg != null) {
            return re.setv("ok", false).setv("msg", msg);
        }
        userService.updatePassword(user.getId(), user.getPassword());
        return re.setv("ok", true);
    }

    @At("/rm")
    @Ok("json")
    public Object rm(@Param("id") long id, @Attr("me") int me) {
        if (id <= 0) {
            return new NutMap().setv("ok", false).setv("msg", "用户不存在!!");
        }
        if (me == id) {
            return new NutMap().setv("ok", false).setv("msg", "不能删除当前用户!!");
        }

        userService.rm(id);
        return new NutMap().setv("ok", true);
    }

    @At("/list")
    @Ok("jsp:view.common.privilege.user_list")
    public Object list(@Param("name") String name, @Param("..") Pager pager) {
        return userService.list(name, pager);
    }

    private String checkUser(User user, boolean create) {
        if (user == null) {
            return "空对象";
        }
        if (create) {
            if (Strings.isBlank(user.getName()) || Strings.isBlank(user.getPassword()))
                return "用户名/密码不能为空";
        } else {
            if (Strings.isBlank(user.getPassword()))
                return "密码不能为空";
        }
        String passwd = user.getPassword().trim();
        if (6 < passwd.length() || passwd.length() > 12) {
            return "密码长度应在6-12位之间";
        }
        user.setPassword(passwd);
        if (create) {
            int count = userService.count(Cnd.where("name", "=", user.getName()));
            if (count != 0) {
                return "用户名已经存在";
            }
        } else {
            if (user.getId() < 1) {
                return "用户Id非法";
            }
        }
        if (user.getName() != null)
            user.setName(user.getName().trim());
        return null;
    }


}
