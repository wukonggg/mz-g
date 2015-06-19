package band.wukong.mz.g.sale.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_cart")
@View("v_cart")
public class Cart {

    @Column("user_id")
    private long userId;

    @Column("cust_id")
    private long custId;

    @Column("sku_more_id")
    private long skuMoreId;

    private double payment;

    @Column
    private int count;  //购买数量

    @Column
    @Readonly
    private String cid;

    @Column
    @Readonly
    private String name;

    @Column
    @Readonly
    private String msisdn;

    @Column
    @Readonly
    private String img;

    @Column
    @Readonly
    private String gname;

    @Column("cate_code")
    @Readonly
    private String cateCode;

    @Column
    @Readonly
    private String model;

    @Column
    @Readonly
    private long sprice;

    @Column
    @Readonly
    private String size;

    @Column
    @Readonly
    private int scount; //库存量

    @Column
    @Readonly
    private String sid;

    @Column("payment_clothing")
    @Readonly
    private long paymentClothing;

    public Cart() {
    }

    public Cart(long userId, long custId, long skuMoreId, int count) {
        this.userId = userId;
        this.custId = custId;
        this.skuMoreId = skuMoreId;
        this.count = count;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public long getSkuMoreId() {
        return skuMoreId;
    }

    public void setSkuMoreId(long skuMoreId) {
        this.skuMoreId = skuMoreId;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getSprice() {
        return sprice;
    }

    public void setSprice(long sprice) {
        this.sprice = sprice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getScount() {
        return scount;
    }

    public void setScount(int scount) {
        this.scount = scount;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public long getPaymentClothing() {
        return paymentClothing;
    }

    public void setPaymentClothing(long paymentClothing) {
        this.paymentClothing = paymentClothing;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", custId=" + custId +
                ", skuMoreId=" + skuMoreId +
                ", payment=" + payment +
                ", count=" + count +
                ", cid='" + cid + '\'' +
                ", name='" + name + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", img='" + img + '\'' +
                ", gname='" + gname + '\'' +
                ", cateCode='" + cateCode + '\'' +
                ", model='" + model + '\'' +
                ", sprice=" + sprice +
                ", size='" + size + '\'' +
                ", scount=" + scount +
                ", sid='" + sid + '\'' +
                ", paymentClothing=" + paymentClothing +
                '}';
    }
}
