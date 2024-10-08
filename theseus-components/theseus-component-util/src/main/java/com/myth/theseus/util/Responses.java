package com.myth.theseus.util;



import com.myth.theseus.model.exception.BaseException;
import com.myth.theseus.model.page.Page;
import com.myth.theseus.model.page.PageCollection;
import com.myth.theseus.model.response.MultiResponse;
import com.myth.theseus.model.response.PageResponse;
import com.myth.theseus.model.response.Response;
import com.myth.theseus.model.response.SingleResponse;

import java.util.Collection;

public final class Responses {
	public static Response success() {
		return new Response();
	}

	public static <T> SingleResponse<T> success(T data) {
		return new SingleResponse<>(data);
	}

	public static <E> MultiResponse<E> success(Collection<E> data) {
		return new MultiResponse<>(data);
	}

	public static <E> PageResponse<E> success(Page page) {
		return new PageResponse<>(page);
	}

	public static <E> PageResponse<E> success(Page page, int totalCount, Collection<E> data) {
		return new PageResponse<>(page, totalCount, data);
	}

	public static <E> PageResponse<E> success(PageCollection<E> pageCollection) {
		return new PageResponse<>(pageCollection.page(), pageCollection.count(), pageCollection.getData());
	}

	public static Response fail(BaseException error) {
		return new Response(error);
	}

	public static <T> SingleResponse<T> failSingle(BaseException error) {
		return new SingleResponse<>(error);
	}

	public static <E> MultiResponse<E> failMulti(BaseException error) {
		return new MultiResponse<>(error);
	}

	public static <T> PageResponse<T> failPage(BaseException error, Page page) {
		return new PageResponse<>(error, page);
	}
}