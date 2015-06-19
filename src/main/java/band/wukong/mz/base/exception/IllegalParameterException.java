package band.wukong.mz.base.exception;

/**
 * 入参不合法异常
 *
 * @author wukong(wukonggg@139.com)
 */
public class IllegalParameterException extends AppRuntimeException {
    private static final String MSG = "Illegal parameter was found during pre-check.";

    /**
     * 默认无参构造方法会自动载入一个默认的message。
     */
    public IllegalParameterException() {
        super(MSG);
    }

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParameterException(Throwable cause) {
        super(cause);
    }

    public IllegalParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
