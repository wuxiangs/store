package com.aisino.store.service.ex;

/**
 * @author wuxiang
 * @date 2021/12/13 3:37 下午
 * 删除数据时产生的异常
 */
public class DeleteException extends ServiceException {



    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    protected DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
