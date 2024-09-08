package com.alibaba.cola.dto.response;

import com.alibaba.cola.dto.exception.BaseException;
import com.alibaba.cola.dto.response.Response;

/**
 * Response with single record to return
 * <p/>
 * Created by Danny.Lee on 2017/11/1.
 */
public class SingleResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    protected final T data;

    public SingleResponse(T data) {
        super();
        this.data = data;
    }

    public SingleResponse(BaseException error) {
        super(error);
        this.data = null;
    }

    public T getData() {
        return data;
    }
}
