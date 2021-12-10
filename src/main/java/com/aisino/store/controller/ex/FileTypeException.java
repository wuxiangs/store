package com.aisino.store.controller.ex;

/**
 * @author wuxiang
 * @date 2021/12/8 10:09 上午
 * 文件上传类型异常类
 */
public class FileTypeException extends FileUploadException{

    public FileTypeException() {
        super();
    }

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeException(Throwable cause) {
        super(cause);
    }

    protected FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
