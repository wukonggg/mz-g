package band.wukong.mz.g.sku.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.File;
import java.util.Date;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_goods")
public class Goods {

    /**
     * 商品状态-删除
     */
    public static final String STATE_RM = "0";

    /**
     * 商品状态-有效
     */
    public static final String STATE_OK = "1";

    /**
     * 默认图片文件名
     */
    public static final String IMG_DFT = "goods_default.png";

    @Id
    private long id;

    @Column("cate_code")
    private String cateCode;

    @Column("gname")
    private String gname;

    @Column
    private String img; //图片名称：flower.png

    @Column
    private String words;

    @Column
    private Date ctime;

    @Column
    private Date utime;

    @Column
    private String state;

    private File gimg;  //module中用到的，用于接收http request中的file

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public File getGimg() {
        return gimg;
    }

    public void setGimg(File gimg) {
        this.gimg = gimg;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", cateCode='" + cateCode + '\'' +
                ", gname='" + gname + '\'' +
                ", img='" + img + '\'' +
                ", words='" + words + '\'' +
                ", ctime=" + ctime +
                ", utime=" + utime +
                ", state='" + state + '\'' +
                ", gimg=" + gimg +
                '}';
    }
}
