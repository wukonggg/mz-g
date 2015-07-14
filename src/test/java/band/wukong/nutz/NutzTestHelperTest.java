package band.wukong.nutz;

import band.wukong.mz.g.sku.service.SidGenerator;
import band.wukong.mz.nutz.NutzTestHelper;
import org.junit.Test;
import org.nutz.ioc.Ioc;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class NutzTestHelperTest {

    @Test
    public void getBeanDefByJsonFile() {
        Ioc ioc = NutzTestHelper.createIoc();
        SidGenerator generator = ioc.get(SidGenerator.class);
        System.out.println("Class of SidGenerator: " + generator.getClass());
    }

}
