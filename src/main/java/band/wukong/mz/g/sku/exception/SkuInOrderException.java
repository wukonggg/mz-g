package band.wukong.mz.g.sku.exception;

import band.wukong.mz.base.exception.AppRuntimeException;

/**
 * sku在订单中存在（无法删除）异常
 *
 * @author wukong(wukonggg@139.com)
 */
public class SkuInOrderException extends AppRuntimeException {

    private static final long serialVersionUID = -8910526263088357852L;

    public SkuInOrderException() {
    }

    public SkuInOrderException(String message) {
        super(message);
    }

    public SkuInOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SkuInOrderException(Throwable cause) {
        super(cause);
    }

    public SkuInOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
