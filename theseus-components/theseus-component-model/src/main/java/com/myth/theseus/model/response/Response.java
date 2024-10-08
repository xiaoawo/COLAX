package com.myth.theseus.model.response;

import com.myth.theseus.model.exception.BaseException;

import java.io.Serializable;

/**
 * Response to caller
 *
 * @author fulan.zjf 2017年10月21日 下午8:53:17
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private final boolean success;

    private final BaseException error;

    @Override
    public String toString() {
        return "Response [success=" + success + ", errorCode=" + error.getErrorCode() + ", errorMessage=" + error.getMessage() + "]";
    }

    public Response() {
        this.success = true;
        this.error = BaseException.EMPTY_ERROR;
    }

    public Response(BaseException error) {
        this.success = false;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public BaseException getError() {
        return error;
    }
}
