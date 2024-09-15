package com.github.xiaowo.model.page;

import java.io.Serializable;


public final class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Page DEFAULT = new Page(1);

    private static final int DEFAULT_SIZE = 10;
    private final int size;
    private final int index;
    private final int offset;

    public Page(int index) {
        this(index, DEFAULT_SIZE);
    }

    public Page(int index, int size) {
        if (index < 1) {
            index = 1;
        }
        this.index = index;
        if (size < 1) {
            size = DEFAULT_SIZE;
        }
        this.size = size;
        this.offset = (this.index - 1) * this.size;
    }

    public int index() {
        return index;
    }

    public int size() {
        return size;
    }

    public int offset() {
        return offset;
    }
}
