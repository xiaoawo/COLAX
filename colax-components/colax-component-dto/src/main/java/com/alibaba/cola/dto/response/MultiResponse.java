package com.alibaba.cola.dto.response;

import com.alibaba.cola.dto.exception.BaseException;

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
    }

    public MultiResponse(BaseException error) {
        super(error);
    }

    public List<E> getData() {
        if (null == data) {
            return Collections.emptyList();
        }
        if (data instanceof List) {
            return (List<E>) data;
        }
        return new ArrayList<>(data);
    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }
}
