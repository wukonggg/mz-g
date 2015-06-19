package band.wukong.mz.g.sale.dao.impl;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.g.sale.bean.Item;
import band.wukong.mz.g.sale.bean.Sku4Item;
import band.wukong.mz.g.sale.dao.ItemDao;
import band.wukong.mz.g.sale.dao.ItemDaoValidator;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "itemDao")
public class ItemDaoImpl implements ItemDao {

    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    @Override
    public void save(Item i) {
        if (!ItemDaoValidator.save(i)) {
            throw new IllegalParameterException();
        }
        dao.insert(i);
    }

    @Override
    public Item find(long id) {
        return dao.fetch(Item.class, id);
    }

    @Override
    public void update(Item i) {
        dao.update(i);
    }

    @Override
    public Item findWithOrder(long id) {
        if (id <= 0) {
            throw new IllegalParameterException();
        }

        Item item = dao.fetch(Item.class, id);
        if (null != item) {
            dao.fetchLinks(item, "order");
        }
        return item;
    }

    @Override
    public List<Item> listWithSkuByOrder(long orderId) {
        String exp =
                "select i.id as 'i.id', i.sku_id as 'i.sku_id', i.sku_more_id as 'i.sku_more_id'\n" +
                        "     , i.dprice as 'i.dprice', i.dcount as 'i.dcount', i.payment as 'i.payment'\n" +
                        "     , i.return_time as 'i.return_time', i.return_reason as 'i.return_reason'" +
                        "     , i.return_desc as 'i.return_desc'\n" +
                        "     , i.state as 'i.state', i.order_id as 'i.order_id'\n" +
                        "     , g.cate_code as 's.cate_code', g.gname as 's.gname', sku.img as 's.img'\n" +
                        "     , sku.model as 's.model', sku.sprice as 's.sprice', sm.size as 's.size'\n" +
                        "from t_item i\n" +
                        "inner join t_sku sku on sku.id = i.sku_id\n" +
                        "inner join t_sku_more sm on sm.id = i.sku_more_id\n" +
                        "inner join t_goods g on g.id = sku.goods_id\n" +
                        "where i.order_id = @orderId";

        Sql sql = Sqls.queryRecord(exp);
        sql.params().set("orderId", orderId);

        dao.execute(sql);
        List<Record> list = sql.getList(Record.class);
        List<Item> itemList = new ArrayList<Item>();
        for (Record re : list) {
            Item item = re.toEntity(dao.getEntity(Item.class), "i.");
            Sku4Item skuInfo = re.toEntity(dao.getEntity(Sku4Item.class), "s.");
            item.setSku(skuInfo);
            itemList.add(item);
        }

        return itemList;
    }
}

