package band.wukong.mz.g.sku.service;

import band.wukong.mz.g.sku.bean.Sku;

import java.util.UUID;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class UuidSidGenerator implements SidGenerator {

    @Override
    public String nextSid(String cateCode, Sku sku) {
        return UUID.randomUUID().toString();
    }

}
