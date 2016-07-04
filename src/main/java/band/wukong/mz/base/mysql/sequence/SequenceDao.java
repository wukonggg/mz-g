package band.wukong.mz.base.mysql.sequence;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public interface SequenceDao {

    public int currval(Sequence seq);

    public int nextval(Sequence seq);
}
