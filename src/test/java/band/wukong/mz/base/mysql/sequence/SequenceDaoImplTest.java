package band.wukong.mz.base.mysql.sequence;

import band.wukong.mz.nutz.test.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class SequenceDaoImplTest {

    private static final Log log = Logs.get();
    private Ioc ioc;
    private SequenceDaoImpl sequenceDaoImpl;

    @Before
    public void setUp() throws ClassNotFoundException {
        ioc = NutzTestHelper.createIoc();
        sequenceDaoImpl = ioc.get(SequenceDaoImpl.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void currval() {
        int currval = sequenceDaoImpl.currval(GallerySequence.S_A);
        log.info("currval=" + currval);
        Assert.assertTrue(currval > 0);
    }


    @Test
    public void nextval() {
        int currval = sequenceDaoImpl.currval(GallerySequence.S_A);
        log.info("currval=" + currval);

        int nextval = sequenceDaoImpl.nextval(GallerySequence.S_A);
        log.info("nextval=" + nextval);

        Assert.assertTrue(nextval > 0);
        Assert.assertTrue(nextval - currval == 1);
    }
}
