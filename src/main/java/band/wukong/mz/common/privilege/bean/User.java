package band.wukong.mz.common.privilege.bean;

import band.wukong.mz.base.bean.BaseBean;
import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_user")
public class User extends BaseBean {

    public static final String STATE_OK = "1";
    public static final String STATE_RM = "2";

    @Id
    protected long id;

    @Name
    @Column
    protected String name;

    @Column("passwd")
    @ColDefine(width=128)
    protected String password;

    private String salt;

    @Column
    private boolean locked;

    @Column
    private String state;

    @ManyMany(from="u_id", relation="t_user_role", target=Role.class, to="role_id")
    protected List<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", locked=" + locked +
                ", state='" + state + '\'' +
                ", roles=" + roles +
                '}';
    }
}
