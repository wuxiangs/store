package com.aisino.store.service.ex;

/**
 * @author wuxiang
 * @date 2021/12/14 1:55 下午
 * 商品信息异常类
 */
public class ProductNotFoundException extends ServiceException{
    public ProductNotFoundException() {

    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
