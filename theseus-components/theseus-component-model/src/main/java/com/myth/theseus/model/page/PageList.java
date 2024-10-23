package com.myth.theseus.model.page;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageList<E> implements PageCollection<E>{
	private final Page page;
	private final int count;
	private final List<E> data;

	public PageList(Page page) {
		this(page, 0, Collections.emptyList());
	}

	public PageList(Page page, int count, List<E> data) {
		assert page != null : "pageInfo is not null";
		assert count >= 0 : "totalCount must be greater than or equal to 0";
		assert data != null : "data is not null";
		this.page = page;
		this.count = count;
		this.data = data;
	}

	@Override
	public List<E> getData() {
		return data;
	}

	@Override
	public Page page() {
		return page;
	}

	@Override
	public int count() {
		return count;
	}

	@Override
	public <R> PageCollection<R> convert(Function<E, R> convertor) {
		List<R> newData = this.getData().stream().map(convertor).collect(Collectors.toList());
		return new PageList<>(page, count, newData);
	}
}