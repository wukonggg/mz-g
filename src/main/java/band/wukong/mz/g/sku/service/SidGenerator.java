package band.wukong.mz.g.sku.service;

/**
 * 采用Oracle风格方法命名的sid生成器
 *
 * @author wukong(wukonggg@139.com)
 */
public interface SidGenerator {

    String currval(String cateCode);

    String nextval(String cateCode);

}
