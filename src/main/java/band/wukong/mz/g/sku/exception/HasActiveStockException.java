package band.wukong.mz.g.sku.exception;

import band.wukong.mz.base.exception.AppRuntimeException;

/**
 * 仍然有有效库存运行时异常。比如会在删除一个仍有有效sku的goods时抛出
 *
 * @author wukong(wukonggg@139.com)
 */
public class HasActiveStockException extends AppRuntimeException {

    private static final long serialVersionUID = -6337242908176205242L;

    public HasActiveStockException() {
    }

    public HasActiveStockException(String message) {
        super(message);
    }

    public HasActiveStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public HasActiveStockException(Throwable cause) {
        super(cause);
    }

    public HasActiveStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
