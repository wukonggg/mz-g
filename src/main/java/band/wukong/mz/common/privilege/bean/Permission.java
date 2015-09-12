package band.wukong.mz.common.privilege.bean;

import band.wukong.mz.base.bean.BaseBean;
import org.nutz.dao.entity.annotation.*;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_permission")
public class Permission extends BaseBean {

    @Id
    protected long id;

    @Name
    protected String name;

    @Column("al")
    protected String alias;

    @Column("dt")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
