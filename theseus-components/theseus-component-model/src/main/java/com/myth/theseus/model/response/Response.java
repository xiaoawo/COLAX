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

    private final ErrorMessage errorMessage;

    @Override
    public String toString() {
        if (success) {
            return "Response [success=" + success + "]";
        }
        return "Response [success=" + success + ", errorCode=" + errorMessage.getCode() + ", errorMessage=" + errorMessage.getMessage() + "]";
    }

    public Response() {
        this.success = true;
        this.errorMessage = null;
    }

    public Response(BaseException exception) {
        this.success = false;
        this.errorMessage = new ErrorMessage(exception.getCode(), exception.getMessage());
    }

    public boolean isSuccess() {
        return success;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
