package band.wukong.mz.nutz;

import band.wukong.mz.g.AppConst;
import org.nutz.dao.pager.Pager;

/**
 * Nutz dao 帮助类
 *
 * @author wukong(wukonggg@139.com)
 */
public class NutzDaoHelper {

    /**
     * 创建分页的Pager。注意：本方法自己new了page，原本nutz提供的Dao是在自身的createPage方法中的
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return
     */
    public static Pager createPager(int pageNum, int pageSize, int count) {
        pageSize = pageSize <= 0 ? AppConst.PAGE_SIZE : pageSize;
        pageNum = (pageNum <= 0 || pageNum > (int) Math.ceil((double) count / pageSize))
                ? AppConst.PAGE_NUM_DFT : pageNum;

//        Pager pager = dao.createPager(pageNum, pageSize);
        Pager pager = new Pager();
        pager.setPageNumber(pageNum);
        pager.setPageSize(pageSize);
        pager.setRecordCount(count);
        return pager;
    }
}
