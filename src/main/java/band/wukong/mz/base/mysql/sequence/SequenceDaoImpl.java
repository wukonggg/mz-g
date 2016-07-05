package band.wukong.mz.base.mysql.sequence;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "sequenceDao")
public class SequenceDaoImpl implements SequenceDao {

    @Inject
    private Dao dao;

    public int currval(Sequence seq) {
        Sql sql = Sqls.fetchInt("select currval('$name')");
        sql.vars().set("name", seq.getSeqName());
        dao.execute(sql);
        return sql.getInt();
    }

    public int nextval(Sequence seq) {
        Sql sql = Sqls.fetchInt("select nextval('$name')");
        sql.vars().set("name", seq.getSeqName());
        dao.execute(sql);
        return sql.getInt();
    }
}
