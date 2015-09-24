package band.wukong.mz.nutz.integration.shiro;

import band.wukong.mz.common.privilege.bean.Permission;
import band.wukong.mz.common.privilege.bean.Role;
import band.wukong.mz.common.privilege.bean.User;
import band.wukong.mz.util.Toolkit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.integration.shiro.CaptchaUsernamePasswordToken;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;

/**
 * 用NutDao来实现Shiro的Realm
 *
 * @author wukong(wukonggg@139.com)
 */
public class NutzDaoRealm extends AuthorizingRealm {

    @Inject
    private Dao dao;

    public NutzDaoRealm() {
        this(null, null);
    }

    public NutzDaoRealm(CacheManager cacheManager) {
        this(cacheManager, null);
    }

    public NutzDaoRealm(CredentialsMatcher matcher) {
        this(null, matcher);
    }

    public NutzDaoRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
        setAuthenticationTokenClass(CaptchaUsernamePasswordToken.class);
    }

    public Dao dao() {
        if (dao == null) {
            dao = Mvcs.ctx().getDefaultIoc().get(Dao.class, "dao");
            return dao;
        }
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        int userId = (Integer) principals.getPrimaryPrincipal();
        User user = dao().fetch(User.class, userId);
        if (user == null)
            return null;
        if (user.isLocked())
            throw new LockedAccountException("Account [" + user.getName() + "] is locked.");

        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        user = dao().fetchLinks(user, null);
        if (user.getRoles() != null) {
            dao().fetchLinks(user.getRoles(), null);
            for (Role role : user.getRoles()) {
                auth.addRole(role.getName());
                if (role.getPermissions() != null) {
                    for (Permission p : role.getPermissions()) {
                        auth.addStringPermission(p.getName());
                    }
                }
            }
        }
        if (user.getPermissions() != null) {
            for (Permission p : user.getPermissions()) {
                auth.addStringPermission(p.getName());
            }
        }
        return auth;
    }

    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CaptchaUsernamePasswordToken upToken = (CaptchaUsernamePasswordToken) token;

        if (Strings.isBlank(upToken.getCaptcha()))
            throw new AuthenticationException("验证码不能为空");
        String _captcha = Strings.sBlank(SecurityUtils.getSubject().getSession(true).getAttribute(Toolkit.captcha_attr));
        if (!upToken.getCaptcha().equalsIgnoreCase(_captcha))
            throw new AuthenticationException("验证码错误");

        User user = dao().fetch(User.class, Cnd.where("name", "=", upToken.getUsername()));
        if (user == null)
            return null;
        if (user.isLocked())
            throw new LockedAccountException("Account [" + upToken.getUsername() + "] is locked.");
        SimpleAccount account = new SimpleAccount(user.getId(), user.getPassword(), getName());
        account.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return account;
    }

}
