package band.wukong.mz.g.sku.service;

import band.wukong.mz.g.sku.bean.Sku;

/**
 * 采用Oracle风格方法命名的sid生成器
 *
 * @author wukong(wukonggg@139.com)
 */
public interface SidGenerator {

    /**
     * nextSid。不同的cateCode的sid生成规则也不同
     *
     * @param cateCode cateCode
     * @param sku      sku
     * @return
     */
    String nextSid(String cateCode, Sku sku);

}
