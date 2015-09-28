package band.wukong.mz.g.customer.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

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

    @Column("payment_clothing")
    private long paymentClothing;

    @Column
    private String state;

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

    public long getPaymentClothing() {
        return paymentClothing;
    }

    public void setPaymentClothing(long paymentClothing) {
        this.paymentClothing = paymentClothing;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (paymentClothing != customer.paymentClothing) return false;
        if (cid != null ? !cid.equals(customer.cid) : customer.cid != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (msisdn != null ? !msisdn.equals(customer.msisdn) : customer.msisdn != null)
            return false;
        if (weixin != null ? !weixin.equals(customer.weixin) : customer.weixin != null)
            return false;
        if (birth != null ? !birth.equals(customer.birth) : customer.birth != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null)
            return false;
        if (remark != null ? !remark.equals(customer.remark) : customer.remark != null)
            return false;
        return !(state != null ? !state.equals(customer.state) : customer.state != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (msisdn != null ? msisdn.hashCode() : 0);
        result = 31 * result + (weixin != null ? weixin.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (int) (paymentClothing ^ (paymentClothing >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
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
                ", paymentClothing=" + paymentClothing +
                ", state='" + state + '\'' +
                '}';
    }
}
