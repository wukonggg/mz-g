package band.wukong.mz.g.sku.bean;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.View;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@View("v_sku_more")
public class SkuMoreView {

    @Id
    @Readonly
    private long skuMoreId;

    @Readonly
    private long skuId;

    @Readonly
    private long goodsId;

    @Readonly
    private String gname;

    @Readonly
    private String cateCode;

    @Readonly
    private String sid;

    @Readonly
    private String model;

    @Readonly
    private long pprice;

    @Readonly
    private long sprice;

    @Readonly
    private String img;

    @Readonly
    private String state;   //sku的state

    @Readonly
    private String size;

    @Readonly
    private int count;

    @Readonly
    private String remark;

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    public long getSkuMoreId() {
        return skuMoreId;
    }

    public void setSkuMoreId(long skuMoreId) {
        this.skuMoreId = skuMoreId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getPprice() {
        return pprice;
    }

    public void setPprice(long pprice) {
        this.pprice = pprice;
    }

    public long getSprice() {
        return sprice;
    }

    public void setSprice(long sprice) {
        this.sprice = sprice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SkuMoreView{" +
                "skuId=" + skuId +
                ", skuMoreId=" + skuMoreId +
                ", goodsId=" + goodsId +
                ", gname='" + gname + '\'' +
                ", cateCode='" + cateCode + '\'' +
                ", sid='" + sid + '\'' +
                ", model='" + model + '\'' +
                ", pprice='" + pprice + '\'' +
                ", sprice='" + sprice + '\'' +
                ", img='" + img + '\'' +
                ", state='" + state + '\'' +
                ", size='" + size + '\'' +
                ", count=" + count +
                ", remark='" + remark + '\'' +
                '}';
    }
}
