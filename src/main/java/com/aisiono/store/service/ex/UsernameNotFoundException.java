package com.aisiono.store.service.ex;

/**
 * @author wuxiang
 * @date 2021/12/6 1:46 下午
 * 用户数据不存在异常
 */
public class UsernameNotFoundException extends ServiceException{
    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
