package band.wukong.mz.common.privilege.service;

import band.wukong.mz.common.privilege.bean.User;
import band.wukong.mz.common.privilege.exception.UnAuthorizedException;
import band.wukong.mz.nutz.test.NutzTestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;

/**
 * desc
 *
 * @author wukong(wukonggg@139.com)
 */
public class UserServiceTest {

    private Ioc ioc;
    private UserService service;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        service = ioc.get(UserService.class);
    }

    @Test
    public void login_ok() {
        User user = null;
        try {
            user = service.login("admin", "123456");
        } catch (UnAuthorizedException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(user);
        System.out.println("user = " + user);
    }
}
