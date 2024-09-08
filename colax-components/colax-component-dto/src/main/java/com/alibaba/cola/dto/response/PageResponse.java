package com.alibaba.cola.dto.response;

import com.alibaba.cola.dto.page.PageInfo;
import com.alibaba.cola.dto.exception.BaseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Response with batch page record to return,
 * usually use in page query
 * <p/>
 * Created by xiaochu.lbj on 2020/06/30.
 */
public class PageResponse<E> extends MultiResponse<E> {

    private static final long serialVersionUID = 1L;

    private final PageInfo pageInfo;
    private final int totalCount;

    public PageResponse(PageInfo pageInfo) {
        super(Collections.emptyList());
        this.pageInfo = PageInfo.DEFAULT;
        this.totalCount = 0;
    }

    public PageResponse(PageInfo pageInfo, int totalCount, Collection<E> data) {
        super(data);
        if (pageInfo == null) {
            pageInfo = PageInfo.DEFAULT;
        }
        this.pageInfo = pageInfo;
        if (totalCount < 0) {
            totalCount = 0;
        }
        this.totalCount = totalCount;
    }

    public PageResponse(BaseException error, PageInfo pageInfo) {
        super(error);
        if (pageInfo == null) {
            pageInfo = PageInfo.DEFAULT;
        }
        this.pageInfo = pageInfo;
        this.totalCount = 0;
    }

    public int getPageSize() {
        return pageInfo.getPageSize();
    }

    public int getPageIndex() {
        return pageInfo.getPageIndex();
    }

    public int getTotalPages() {
        return totalCount % pageInfo.getPageSize() == 0 ?
                totalCount / pageInfo.getPageSize() : (totalCount / pageInfo.getPageSize()) + 1;
    }
}
