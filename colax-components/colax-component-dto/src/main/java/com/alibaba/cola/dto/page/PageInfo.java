package com.alibaba.cola.dto.page;

import java.io.Serializable;

/**
 * Page Query Param
 *
 * @author jacky
 */
public final class PageInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final PageInfo DEFAULT = new PageInfo(1);

    private static final int DEFAULT_PAGE_SIZE = 10;
    private final int pageSize;
    private final int pageIndex;
    private final int offset;

    public PageInfo(int pageIndex) {
        this(pageIndex, DEFAULT_PAGE_SIZE);
    }

    public PageInfo(int pageIndex, int pageSize) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        this.pageIndex = pageIndex;
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
        this.offset = (this.pageIndex - 1) * this.pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return offset;
    }
}
