package band.wukong.mz.g.sku.service.impl;

import band.wukong.mz.base.exception.AppRuntimeException;
import band.wukong.mz.g.sku.service.SidGenerator;

import java.util.UUID;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class UuidSidGenerator implements SidGenerator {

    @Override
    public String currval(String cateCode) {
        throw new AppRuntimeException("此方法未实现");
    }

    @Override
    public String nextval(String cateCode) {
        return UUID.randomUUID().toString();
    }
}
