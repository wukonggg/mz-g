package band.wukong.mz.base.mysql.sequence;

import org.junit.Test;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class GallerySequenceTest {

    @Test
    public void forEachOnClass() {
        for (GallerySequence e : GallerySequence.values()) {
            System.out.println(e.getSeqName());
        }
    }

    @Test
    public void forEachOnInterface() {
        for (Sequence s : GallerySequence.values()) {
            System.out.println(s.getSeqName());
        }
    }
}
