package band.wukong.mz.g.sale;

import band.wukong.mz.base.exception.AppRuntimeException;

/**
 * 库存不足Exception
 *
 * @author wukong(wukonggg@139.com)
 */
public class OutOfStockException extends AppRuntimeException {

    public OutOfStockException() {
        super("库存不够");
    }

    public OutOfStockException(String message) {
        super(message);
    }

    public OutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfStockException(Throwable cause) {
        super(cause);
    }

    public OutOfStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
