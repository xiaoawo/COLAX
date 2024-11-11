package com.myth.theseus.helper.gate.filter;


import com.myth.theseus.helper.gate.GateKeeper;
import com.myth.theseus.model.exception.BaseException;
import com.myth.theseus.model.page.Page;
import com.myth.theseus.model.request.PageQuery;
import com.myth.theseus.model.response.MultiResponse;
import com.myth.theseus.model.response.PageResponse;
import com.myth.theseus.model.response.SingleResponse;
import com.myth.theseus.util.Responses;
import com.myth.theseus.util.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class CatchExceptionGateFilter implements GateFilter {

	@Override
	public Object around(ProceedingJoinPoint point, GateKeeper gateKeeper) throws Throwable {
		Object[] args = new Object[]{};
		Class<?> returnType = null;
		Object result;
		try {
			args = point.getArgs();
			MethodSignature ms = (MethodSignature) point.getSignature();
			returnType = ms.getReturnType();
			result = point.proceed();
		} catch (Throwable t) {
			result = handleException(args, returnType, t);
		}

		return result;
	}

	private Object handleException(Object[] args, Class<?> returnType, Throwable t) {
		// 打印异常信息并处理返回结果
		if (t instanceof BaseException) {
			BaseException error = (BaseException) t;
			log.error(error.toString(), t);
			return buildResponse(args, returnType, error);
		} else {
			log.error(t.getMessage(), t);
			return buildResponse(args, returnType, Exceptions.sysException(t.getMessage()));
		}
	}

	private Object buildResponse(Object[] args, Class<?> returnType, BaseException error) {
		if (Objects.equals(returnType, SingleResponse.class)) {
			return Responses.failSingle(error);
		} else if (Objects.equals(returnType, MultiResponse.class)) {
			return Responses.failMulti(error);
		} else if (Objects.equals(returnType, PageResponse.class)) {
			// 寻找Page
			Page page = Page.DEFAULT;
			for (Object arg : args) {
				if (arg instanceof Page) {
					page = (Page) arg;
					break;
				} else if (arg instanceof PageQuery) {
					PageQuery pageQuery = (PageQuery) arg;
					page = pageQuery.getPage();
					break;
				}
			}
			return Responses.failPage(error, page);
		}

		return Responses.fail(error);
	}
}