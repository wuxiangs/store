package com.aisino.store.controller.ex;

/**
 * @author wuxiang
 * @date 2021/12/8 10:08 上午
 * 文件上传io异常类
 */
public class FileUploadIoException extends FileUploadException{

    public FileUploadIoException() {
        super();
    }

    public FileUploadIoException(String message) {
        super(message);
    }

    public FileUploadIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIoException(Throwable cause) {
        super(cause);
    }

    protected FileUploadIoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
