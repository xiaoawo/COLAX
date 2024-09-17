package com.github.colax.model.page;

import java.util.Collection;

public interface PageCollection<E> {
	Collection<E> getData();

	Page page();

	int count();

	default int pages() {
		return count() % page().size() == 0 ? count() / page().size() : (count() / page().size()) + 1;
	}

	default boolean hasMore() {
		if (isEmpty()) {
			return false;
		}
		if (getData().size() >= count()) {
			// 暂无更多数据
			return false;
		}

		return page().index() < pages();
	}

	default boolean isEmpty() {
		return getData() == null || getData().isEmpty();
	}

	default boolean isNotEmpty() {
		return !isEmpty();
	}
}
