package com.myth.theseus.model.util;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface StreamList<E> extends List<E> {
	<R> StreamList<R> map(Function<E, R> mapper);

	StreamList<E> filter(Predicate<E> predicate);
}
