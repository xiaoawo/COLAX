package com.github.colax.model.exception;

/**
 * Base Exception is the parent of all exceptions
 *
 * @author fulan.zjf 2017年10月22日 上午12:00:39
 */
public class BaseException extends RuntimeException {

    public static final BaseException EMPTY_ERROR = new BaseException("", "");

    private static final long serialVersionUID = 1L;

    private String code;

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
    }

    public BaseException(String errCode, String message, Throwable e) {
        super(message, e);
        this.code = errCode;
    }

    public String getErrorCode() {
        return code;
    }
}
