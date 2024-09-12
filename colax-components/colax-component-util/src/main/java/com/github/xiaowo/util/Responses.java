package com.github.xiaowo.util;


import com.alibaba.cola.dto.exception.BaseException;
import com.alibaba.cola.dto.page.PageInfo;
import com.alibaba.cola.dto.response.MultiResponse;
import com.alibaba.cola.dto.response.PageResponse;
import com.alibaba.cola.dto.response.Response;
import com.alibaba.cola.dto.response.SingleResponse;

import java.util.Collection;

public final class Responses {
	public static Response success() {
		return new Response();
	}

	public static <T> SingleResponse<T> success(T data) {
		return new SingleResponse<>(data);
	}

	public static <E> MultiResponse<E> success(Collection<E> data) {
		return new MultiResponse<E>(data);
	}

	public static <E> PageResponse<E> success(PageInfo pageInfo) {
		return new PageResponse<>(pageInfo);
	}

	public static <E> PageResponse<E> success(PageInfo pageInfo, int totalCount, Collection<E> data) {
		return new PageResponse<>(pageInfo, totalCount, data);
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

	public static <T> PageResponse<T> failPage(BaseException error, PageInfo pageInfo) {
		return new PageResponse<>(error, pageInfo);
	}
}