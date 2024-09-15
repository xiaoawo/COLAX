package com.github.xiaowo.dto.response;

import com.github.xiaowo.dto.exception.BaseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Response with batch record to return,
 * usually use in conditional query
 * <p/>
 * Created by Danny.Lee on 2017/11/1.
 */
public class MultiResponse<E> extends SingleResponse<Collection<E>> {

    private static final long serialVersionUID = 1L;

    public MultiResponse() {
        super(Collections.emptyList());
    }

    public MultiResponse(Collection<E> data) {
        super(data);
        assert data != null : "data should not be null";
    }

    public MultiResponse(BaseException error) {
        super(error);
    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }
}