package com.github.xiaowo.model.page;


import com.github.xiaowo.model.iterator.Scanner;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

public final class PageScanner<E> implements Scanner<E>, PageCollection<E> {
	private final Page startPage;
	private final Function<Page, PageCollection<E>> function;
	private final int count;
	private int currentPageIndex;
	private Iterator<E> currentIterator;
	private PageCollection<E> currentPageCollection;

	public PageScanner(Page startPage, Function<Page, PageCollection<E>> function) {
		this.startPage = startPage;
		this.function = function;
		this.currentPageIndex = startPage.index();
		this.currentPageCollection = function.apply(new Page(currentPageIndex, startPage.size()));
		this.count = currentPageCollection.count();
		this.currentIterator = currentPageCollection.getData().iterator();
	}

	private void nextPage() {
		this.currentPageCollection = function.apply(new Page(currentPageIndex, startPage.size()));
		currentIterator = currentPageCollection.getData().iterator();
		currentPageIndex++;
	}

	@Override
	public boolean hasNext() {
		return currentIterator.hasNext() || hasMore();
	}

	@Override
	public E next() {
		if (currentIterator.hasNext()) {
			return currentIterator.next();
		}
		if (hasMore()) {
			nextPage();
		}
		return currentIterator.next();
	}

	@Override
	public Collection<E> getData() {
		return currentPageCollection.getData();
	}

	@Override
	public Page page() {
		return new Page(currentPageIndex, startPage.size());
	}

	@Override
	public int count() {
		return count;
	}

	public boolean hasMore() {
		return currentPageCollection.hasMore();
	}
}
