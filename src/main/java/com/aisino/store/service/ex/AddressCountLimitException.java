package com.aisino.store.service.ex;

/**
 * @author wuxiang
 * @date 2021/12/9 3:13 下午
 * 收货地址条数超出限制
 */
public class AddressCountLimitException extends ServiceException{

    public AddressCountLimitException() {
        super();
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    protected AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
