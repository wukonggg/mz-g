package band.wukong.mz.g.sku.service.impl;

import band.wukong.mz.base.exception.IllegalParameterException;
import band.wukong.mz.base.mysql.sequence.GallerySequence;
import band.wukong.mz.base.mysql.sequence.SequenceDao;
import band.wukong.mz.g.category.SimpleCateConst;
import band.wukong.mz.g.sku.service.SidGenerator;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

/**
 * Category sequence sid 生成器
 *
 * @author wukong(wukonggg@139.com)
 */
@IocBean(name = "sidGenerator")
public class CateSeqSidGenerator implements SidGenerator {

    @Inject
    private SequenceDao sequenceDao;

    @Override
    public String currval(String cateCode) {
        if (Strings.isBlank(cateCode)) {
            throw new IllegalParameterException("cateCode为空");
        }
        GallerySequence seq = chooseSequence(cateCode);
        int currval = sequenceDao.currval(seq);
        return seq.getSeqName().substring(2) + String.valueOf(currval);
    }

    @Override
    public String nextval(String cateCode) {
        if (Strings.isBlank(cateCode)) {
            throw new IllegalParameterException("cateCode为空");
        }
        GallerySequence seq = chooseSequence(cateCode);
        int nextval = sequenceDao.nextval(seq);
        return seq.getSeqName().substring(2) + String.valueOf(nextval);
    }

    private GallerySequence chooseSequence(String cateCode) {
        GallerySequence seq = null;
        switch (cateCode) {
            case SimpleCateConst.CATE_CODE_A_SYTZ:
                seq = GallerySequence.S_A;
                break;
            case SimpleCateConst.CATE_CODE_B_KZ:
                seq = GallerySequence.S_B;
                break;
            case SimpleCateConst.CATE_CODE_C_NY:
                seq = GallerySequence.S_C;
                break;
            case SimpleCateConst.CATE_CODE_D_XZ:
                seq = GallerySequence.S_D;
                break;
            case SimpleCateConst.CATE_CODE_E_RH:
                seq = GallerySequence.S_E;
                break;
            case SimpleCateConst.CATE_CODE_F_LSBJ:
                seq = GallerySequence.S_F;
                break;
            case SimpleCateConst.CATE_CODE_G_NF:
                seq = GallerySequence.S_G;
                break;
            case SimpleCateConst.CATE_CODE_H_WJ:
                seq = GallerySequence.S_H;
                break;
            case SimpleCateConst.CATE_CODE_I_NBS:
                seq = GallerySequence.S_I;
                break;
            case SimpleCateConst.CATE_CODE_J_WJ:
                seq = GallerySequence.S_J;
                break;

            default:
                throw new IllegalParameterException("非法的CateCode，无法获取sequence。");
        }
        return seq;
    }
}
