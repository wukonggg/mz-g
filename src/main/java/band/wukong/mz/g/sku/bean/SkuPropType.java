package band.wukong.mz.g.sku.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_sku_prop_type")
public class SkuPropType {

    public static final String ITEM_SIZE_STANDARD = "SIZE_STANDARD";
    public static final String NAME_DEFAULT = "DEFAULT";

    @Column("cate_code")
    private String cateCode;

    @Column
    private String item;

    @Column
    private String name;

    @Column
    private String title;

    @Column
    private String evalue;

    @Column
    private String cvalue;

    @Column("name_order")
    private int nameOrder;

    @Column("value_order")
    private int valueOrder;

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvalue() {
        return evalue;
    }

    public void setEvalue(String evalue) {
        this.evalue = evalue;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public int getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(int nameOrder) {
        this.nameOrder = nameOrder;
    }

    public int getValueOrder() {
        return valueOrder;
    }

    public void setValueOrder(int valueOrder) {
        this.valueOrder = valueOrder;
    }

    @Override
    public String toString() {
        return "SkuDict{" +
                "cateCode='" + cateCode + '\'' +
                ", item='" + item + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", evalue='" + evalue + '\'' +
                ", cvalue='" + cvalue + '\'' +
                ", nameOrder=" + nameOrder +
                ", valueOrder=" + valueOrder +
                '}';
    }
}
