package band.wukong.mz.g.sku.bean;

import org.nutz.dao.entity.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_sku")
public class Sku {

    /**
     * 商品状态-删除
     */
    public static final String STATE_RM = "0";

    /**
     * 商品状态-上架
     */
    public static final String STATE_ON = "1";

    /**
     * 商品状态-下架
     */
    public static final String STATE_OFF = "2";

    /**
     * 默认图片文件名
     */
    public static final String IMG_DFT = "sku_default.png";

    @Id
    private long id;

    @Name
    private String sid;

    @Column
    private String model;

    @Column
    private String type;

    @Column
    private Date ptime;

    @Column
    private long pprice;

    @Column
    private long sprice;

    @Column
    private String img;

    @Column
    private Date ctime;

    @Column
    private Date utime;

    @Column
    private String state;

    @Column("goods_id")
    private long goodsId;

    @One(target = Goods.class, field = "goodsId")
    private Goods goods;

    @Many(target = SkuMore.class, field = "skuId")
    private List<SkuMore> moreList;

    private File gimg;

    private int count;  //sku list中用于显示库存数量

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
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

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<SkuMore> getMoreList() {
        return moreList;
    }

    public void setMoreList(List<SkuMore> moreList) {
        this.moreList = moreList;
    }

    public File getGimg() {
        return gimg;
    }

    public void setGimg(File gimg) {
        this.gimg = gimg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Sku{" +
            "id=" + id +
            ", sid='" + sid + '\'' +
            ", model='" + model + '\'' +
            ", type='" + type + '\'' +
            ", ptime=" + ptime +
            ", pprice=" + pprice +
            ", sprice=" + sprice +
            ", img='" + img + '\'' +
            ", ctime=" + ctime +
            ", utime=" + utime +
            ", state='" + state + '\'' +
            ", goodsId=" + goodsId +
            ", goods=" + goods +
            ", moreList=" + moreList +
            ", gimg=" + gimg +
            ", count=" + count +
            '}';
    }
}
