package band.wukong.mz.g.privilege.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author wukong(wukonggg@139.com)
 */
@Table("t_user")
public class User {
    @Id
    private Long id;

    @Column("login_name")
    private String loginName;

    @Column
    private transient String pwd;

    @Column
    private Integer stat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String login_name) {
        this.loginName = login_name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", stat=" + stat +
                '}';
    }
}
