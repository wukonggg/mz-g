package band.wukong.mz.common.privilege.bean;

import band.wukong.mz.base.bean.BaseBean;
import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_role")
public class Role extends BaseBean {

    @Id
    protected long id;

    @Name
    protected String name;

    @Column("al")
    protected String alias;

    @Column("dt")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String description;

    @ManyMany(from = "role_id", relation = "t_role_permission", target = Permission.class, to = "permission_id")
    protected List<Permission> permissions;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
