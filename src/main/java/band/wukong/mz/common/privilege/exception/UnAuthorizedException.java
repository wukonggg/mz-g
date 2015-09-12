package band.wukong.mz.common.privilege.exception;

import band.wukong.mz.base.exception.AppException;

/**
 * As you see...
 *
 * @author wukong(wukonggg@139.com)
 */
public class UnAuthorizedException extends AppException {

    public UnAuthorizedException() {
    }

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizedException(Throwable cause) {
        super(cause);
    }

    public UnAuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
