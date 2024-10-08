package com.myth.theseus.model.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArrayStreamList<E> extends ArrayList<E> implements StreamList<E> {


	public ArrayStreamList() {
	}

	public ArrayStreamList(Collection<? extends E> c) {
		super(c);
	}

	public ArrayStreamList(int initialCapacity) {
		super(initialCapacity);
	}

	@Override
	public <R> StreamList<R> map(Function<E, R> mapper) {
		ArrayStreamList<R> arrayStreamList = new ArrayStreamList<>();
		if (this.isEmpty()) {
			return arrayStreamList;
		}

		for (E e : this) {
			arrayStreamList.add(mapper.apply(e));
		}

		return arrayStreamList;
	}

	@Override
	public StreamList<E> filter(Predicate<E> predicate) {
		ArrayStreamList<E> arrayStreamList = new ArrayStreamList<>();
		if (this.isEmpty()) {
			return arrayStreamList;
		}

		for (E e : this) {
			if (predicate.test(e)) {
				arrayStreamList.add(e);
			}
		}

		return arrayStreamList;
	}
}