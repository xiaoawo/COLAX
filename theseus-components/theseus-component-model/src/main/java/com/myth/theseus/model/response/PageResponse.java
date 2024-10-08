package com.myth.theseus.model.response;

import com.myth.theseus.model.exception.BaseException;
import com.myth.theseus.model.page.Page;
import com.myth.theseus.model.page.PageCollection;

import java.util.Collection;
import java.util.Collections;

/**
 * Response with batch page record to return,
 * usually use in page query
 * <p/>
 * Created by xiaochu.lbj on 2020/06/30.
 */
public class PageResponse<E> extends MultiResponse<E> implements PageCollection<E> {

    private static final long serialVersionUID = 1L;

    private final Page page;
    private final int count;

    public PageResponse(Page page) {
        super(Collections.emptyList());
        assert page != null : "pageInfo is not null";
        this.page = page;
        this.count = 0;
    }

    public PageResponse(Page page, int count, Collection<E> data) {
        super(data);
        assert page != null : "pageInfo is not null";
        assert count >= 0 : "count must be greater than or equal to 0";
        this.page = page;
        this.count = count;
    }

    public PageResponse(BaseException error, Page page) {
        super(error);
        assert page != null : "pageInfo is not null";
        this.page = page;
        this.count = 0;
    }

    @Override
    public Page page() {
        return page;
    }

    @Override
    public int count() {
        return count;
    }
}
