package band.wukong.mz.g.category.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_cate")
public class Category {
    /**
     * CATE_CODE类型——SIMPLE
     */
    public static final String CATE_CODE_TYPE_SIMPLE = "S";

    public static final String STATE_OK = "1";  //状态-有效
    public static final String STATE_RM = "0";  //状态-已删除

    @Name
    private String code;

    @Column
    private String pcode;

    @Column
    private String title;

    @Column("ord")
    private int order;

    @Column
    private int seq;

    @Column
    private String remark;

    @Column
    private String state;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Category{" +
                "code='" + code + '\'' +
                ", pcode='" + pcode + '\'' +
                ", title='" + title + '\'' +
                ", order=" + order +
                ", seq=" + seq +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
