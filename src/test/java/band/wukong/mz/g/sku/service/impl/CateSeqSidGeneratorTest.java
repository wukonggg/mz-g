package band.wukong.mz.g.sku.service.impl;

import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.nutz.test.NutzTestHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class CateSeqSidGeneratorTest {
    private Ioc ioc;
    private CateSeqSidGenerator gen;

    @Before
    public void setUp() {
        ioc = NutzTestHelper.createIoc();
        gen = ioc.get(CateSeqSidGenerator.class);
    }

    @After
    public void tearDown() {
        NutzTestHelper.destroyIoc(ioc);
    }

    @Test
    public void currval() {
        String seq = gen.currval(SimpleCateConst.CATE_CODE_A_SYTZ);
        System.out.println("seq = " + seq);
        Assert.assertNotNull(seq);
    }

    @Test
    public void nextval() {
        String currseq = gen.currval(SimpleCateConst.CATE_CODE_A_SYTZ);
        System.out.println("currseq = " + currseq);
        String nextseq = gen.nextval(SimpleCateConst.CATE_CODE_A_SYTZ);
        System.out.println("nextseq = " + nextseq);
        Assert.assertNotNull(nextseq);
    }
}
