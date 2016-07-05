package band.wukong.mz.base.mysql.sequence;

import band.wukong.mz.g.category.SimpleCateConst;

/**
 * Gallery的SequenceEnum实现
 *
 * @author wukong(wukonggg@139.com)
 */
public enum GallerySequence implements Sequence {

    S_A {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_A_SYTZ;
        }
    },
    S_B {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_B_KZ;
        }
    },
    S_C {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_C_NY;
        }
    },
    S_D {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_D_XZ;
        }
    },
    S_E {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_E_RH;
        }
    },
    S_F {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_F_LSBJ;
        }
    },
    S_G {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_G_NF;
        }
    },
    S_H {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_H_WJ;
        }
    },
    S_I {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_I_NBS;
        }
    },
    S_J {
        public String getSeqName() {
            return SimpleCateConst.CATE_CODE_J_WJ;
        }
    },;

    public abstract String getSeqName();

}
