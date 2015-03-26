package cn.buglife.demo.msv.exception;

/**
 * <p>系统默认异常</p>
 *
 * Created by CrazyHarry on 2015/1/1.
 */
public class WifiDefaultException extends Exception {

    public WifiDefaultException(){
        super();
    }

    public WifiDefaultException(String msg){
        super(msg);
    }

    public WifiDefaultException(Throwable cause){
        super(cause);
    }
}
