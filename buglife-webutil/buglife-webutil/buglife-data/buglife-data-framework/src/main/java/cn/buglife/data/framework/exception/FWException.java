package cn.buglife.data.framework.exception;

/**
 * <p><code>FWException</code>是一个自定义的框架级别的异常类，处理框架里的异常信息
 * <p/>
 * 
 * Created by CrazyHarry on 2014/11/26.
 */
public class FWException extends Exception {
    public FWException() {
        super();
    }

    public FWException(String message) {
        super(message);
    }

    public FWException(String message, Throwable cause) {
        super(message, cause);
    }

    public FWException(Throwable cause) {
        super(cause);
    }

    public FWException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
