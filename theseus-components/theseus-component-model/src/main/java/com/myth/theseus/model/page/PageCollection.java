package com.myth.theseus.model.page;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

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

	default <R> PageCollection<R> convert(Function<E, R> convertor) {
		throw new NotImplementedException();
	}
}
