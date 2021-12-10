package com.aisino.store.service.ex;

/**
 * @author wuxiang
 * @date 2021/12/7 10:01 上午
 * 更新异常类
 */
public class UpdateException extends ServiceException {
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
