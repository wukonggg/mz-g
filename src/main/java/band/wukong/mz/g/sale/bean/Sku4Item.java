package band.wukong.mz.g.sale.bean;

/**
 * 专门为order&item的查询准备的sku相关信息封装对象。
 *
 * @author wukong(wukonggg@139.com)
 */
public class Sku4Item {

    private String cateCode;
    private String gname;
    private String model;
    private String size;
    private int sprice;
    private String img;

    public Sku4Item() {
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSprice() {
        return sprice;
    }

    public void setSprice(int sprice) {
        this.sprice = sprice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Sku4Item{" +
                "cateCode='" + cateCode + '\'' +
                ", gname='" + gname + '\'' +
                ", model='" + model + '\'' +
                ", size='" + size + '\'' +
                ", sprice=" + sprice +
                ", img='" + img + '\'' +
                '}';
    }
}
