package com.aisino.store.service.ex;

/**
 * @author wuxiang
 * @date 2021/12/4 9:26 下午
 * 业务层异常的基类
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
