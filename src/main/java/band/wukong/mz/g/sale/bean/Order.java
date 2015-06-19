package band.wukong.mz.g.sale.bean;

import band.wukong.mz.g.customer.bean.Customer;
import org.nutz.dao.entity.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@Table("t_order")
public class Order {
    @Id
    private long id;

    @Column("user_id")
    private long userId;

    @Column("cust_id")
    private long custId;

    @Column
    private Date dtime;

    @Many(target = Item.class, field = "oid")
    private List<Item> items;

    @One(target = Customer.class, field = "custId")
    private Customer cust;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", custId=" + custId +
                ", dtime=" + dtime +
                ", userId=" + userId +
                ", items=" + items +
                ", cust=" + cust +
                '}';
    }
}
