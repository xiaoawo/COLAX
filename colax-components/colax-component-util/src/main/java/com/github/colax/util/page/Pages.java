package com.github.colax.util.page;


import com.github.colax.model.page.Page;
import com.github.colax.model.page.PageCollection;
import com.github.colax.model.page.PageScanner;

import java.util.function.Function;

public final class Pages {
	public static <E> PageScanner<E> scanner(Page startPage, Function<Page, PageCollection<E>> function) {
		return new PageScanner<>(startPage, function);
	}
}