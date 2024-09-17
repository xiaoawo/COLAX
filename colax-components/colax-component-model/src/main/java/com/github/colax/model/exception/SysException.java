package com.github.colax.model.exception;

/**
 * System Exception is unexpected Exception, retry might work again
 *
 * @author Danny.Lee 2018/1/27
 */
public class SysException extends BaseException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_CODE = "SYS_ERROR";

    public SysException(String message) {
        super(DEFAULT_CODE, message);
    }

    public SysException(String code, String message) {
        super(code, message);
    }

    public SysException(String message, Throwable t) {
        super(DEFAULT_CODE, message, t);
    }

    public SysException(String code, String message, Throwable t) {
        super(code, message, t);
    }

}
