package band.wukong.mz.g.privilege.service;

import band.wukong.mz.g.privilege.bean.User;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
public interface UserService {
    /**
     * 登陆。
     *
     * @param u 查询条件仅包括用户名和密码。
     * @return 当没有查到时，返回null。查到时返回用户pojo，其中不含密码
     */
    public User login(User u);

    /**
     * find
     *
     * @param userId user
     * @return
     */
    public User find(long userId);
}
