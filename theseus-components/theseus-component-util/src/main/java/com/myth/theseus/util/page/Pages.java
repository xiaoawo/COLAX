package com.myth.theseus.util.page;


import com.myth.theseus.model.page.Page;
import com.myth.theseus.model.page.PageCollection;
import com.myth.theseus.model.page.PageScanner;

import java.util.function.Function;

public final class Pages {
	public static <E> PageScanner<E> scanner(Page startPage, Function<Page, PageCollection<E>> function) {
		return new PageScanner<>(startPage, function);
	}
}