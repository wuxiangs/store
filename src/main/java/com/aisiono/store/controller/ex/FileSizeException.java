package com.aisiono.store.controller.ex;

/**
 * @author wuxiang
 * @date 2021/12/8 10:07 上午
 * 文件上传大小异常类
 */
public class FileSizeException extends FileUploadException{

    public FileSizeException() {
        super();
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    protected FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
