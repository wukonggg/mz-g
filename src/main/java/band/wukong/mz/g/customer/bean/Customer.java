package band.wukong.mz.g.customer.bean;

import band.wukong.mz.common.privilege.bean.User;
import org.nutz.dao.entity.annotation.*;

import java.util.Date;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_customer")
public class Customer {
    /**
     * 状态-删除
     */
    public static final String STATE_RM = "0";

    /**
     * 状态-有效
     */
    public static final String STATE_OK = "1";

    /**
     * id-非会员顾客
     */
    public static final long NON_MEMBER_ID = 1L;

    /**
     * 会员卡号-非会员顾客
     */
    public static final String NON_MEMBER_CID = "00000004";

    /**
     * 姓名-非会员顾客
     */
    public static final String NON_MEMBER_NAME = "非会员顾客";

    @Id
    private long id;

    @Name
    private String cid; //会员卡id

    @Column
    private String name;

    @Column
    private String msisdn;  //手机号

    @Column
    private String weixin;  //微信

    @Column
    private Date birth; //

    @Column
    private String address;

    @Column
    private String remark;

    @Column
    private Date ctime;

    @Column
    private Date utime;

    @Column
    private String state;

    @Column
    private long uuid;

    @One(target = User.class, field = "uuid")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", cid='" + cid + '\'' +
            ", name='" + name + '\'' +
            ", msisdn='" + msisdn + '\'' +
            ", weixin='" + weixin + '\'' +
            ", birth=" + birth +
            ", address='" + address + '\'' +
            ", remark='" + remark + '\'' +
            ", ctime=" + ctime +
            ", utime=" + utime +
            ", state='" + state + '\'' +
            ", uuid=" + uuid +
            ", user=" + user +
            '}';
    }
}
