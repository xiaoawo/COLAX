package com.github.colax.model.iterator;


import java.util.Iterator;

public interface Scanner<E> extends Iterator<E>, Iterable<E> {
	int count();

	default Iterator<E> iterator() {return this;}
}
