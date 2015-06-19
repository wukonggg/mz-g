package band.wukong.mz.g.sku.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_sku_more")
public class SkuMore {
    @Id
    private long id;

    @Column("sku_id")
    private long skuId;

    @Column
    private String size;

    @Column
    private int count;

    @Column
    private String remark;

    @One(target = Sku.class, field = "skuId")
    private Sku sku;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
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

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "SkuMore{" +
                "id=" + id +
                ", skuId=" + skuId +
                ", size='" + size + '\'' +
                ", count=" + count +
                ", remark='" + remark + '\'' +
                ", sku=" + sku +
                '}';
    }
}
