package band.wukong.mz.g.sale.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_item")
public class Item {

    /**
     * 状态-删除
     */
    public static final String STATE_RM = "0";

    /**
     * 状态-有效
     */
    public static final String STATE_OK = "1";

    /**
     * 状态-退货
     */
    public static final String STATE_RETURN = "2";

    /**
     * 退货原因-质量问题
     */
    public static final String RETURN_REASON_QUANLITY = "1";

    /**
     * 退货原因-尺码不合适
     */
    public static final String RETURN_REASON_SIZE = "2";


    @Id
    private long id;

    @Column("sku_id")
    private long skuid;      //sku主表pk

    @Column("sku_more_id")
    private long skuMoreId;  //skumore表pk

    @Column("cate_code_snapshot")
    private String cateCode;    //cateCode

    @Column("sprice_snapshot")
    private long sprice;     //当前的零售价

    @Column
    private long dprice;     //成交价格

    @Column
    private int dcount;      //成交数量

    @Column
    private long payment;    //付款金额

    @Column("return_time")
    private Date returnTime;        //退货时间

    @Column("return_user_id")
    private long returnUserId;      //退货柜员id

    @Column("return_reason")
    private String returnReason;    //退货原因

    @Column("return_desc")
    private String returnDesc;      //退货描述

    @Column
    private String state;

    @Column("order_id")
    private long oid;

    @One(target = Order.class, field = "oid")
    private Order order;

    private Sku4Item sku;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSkuid() {
        return skuid;
    }

    public void setSkuid(long skuid) {
        this.skuid = skuid;
    }

    public long getSkuMoreId() {
        return skuMoreId;
    }

    public void setSkuMoreId(long skuMoreId) {
        this.skuMoreId = skuMoreId;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public long getSprice() {
        return sprice;
    }

    public void setSprice(long sprice) {
        this.sprice = sprice;
    }

    public long getDprice() {
        return dprice;
    }

    public void setDprice(long dprice) {
        this.dprice = dprice;
    }

    public int getDcount() {
        return dcount;
    }

    public void setDcount(int dcount) {
        this.dcount = dcount;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public long getReturnUserId() {
        return returnUserId;
    }

    public void setReturnUserId(long returnUserId) {
        this.returnUserId = returnUserId;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Sku4Item getSku() {
        return sku;
    }

    public void setSku(Sku4Item sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", skuid=" + skuid +
                ", skuMoreId=" + skuMoreId +
                ", cateCode='" + cateCode + '\'' +
                ", sprice=" + sprice +
                ", dprice=" + dprice +
                ", dcount=" + dcount +
                ", payment=" + payment +
                ", returnTime=" + returnTime +
                ", returnUserId=" + returnUserId +
                ", returnReason='" + returnReason + '\'' +
                ", returnDesc='" + returnDesc + '\'' +
                ", state='" + state + '\'' +
                ", oid=" + oid +
                ", order=" + order +
                ", sku=" + sku +
                '}';
    }
}
