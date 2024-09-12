package com.github.xiaowo.dto.page;

import java.util.Collection;

public interface PageCollection<E> {
	Collection<E> getData();

	Page page();

	int count();

	default int pages() {
		return count() % page().size() == 0 ? count() / page().size() : (count() / page().size()) + 1;
	}
}
