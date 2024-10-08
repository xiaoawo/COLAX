package com.github.colax.helper.gate.filter.exception;


import com.github.colax.helper.gate.GateKeeper;
import com.github.colax.helper.gate.filter.GateFilter;
import com.github.colax.helper.gate.filter.exception.handler.ResponseHandler;
import com.myth.theseus.model.exception.BaseException;
import com.myth.theseus.util.Responses;
import com.myth.theseus.util.exception.Exceptions;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatchExceptionGateFilter implements GateFilter {

	@Resource
	private List<ResponseHandler> responseHandlerList;

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
			result = handleException(args, returnType, t, gateKeeper);
		}

		return result;
	}

	private Object handleException(Object[] args, Class<?> returnType, Throwable t, GateKeeper gateKeeper) {
		for (ResponseHandler handler : responseHandlerList) {
			if (handler.isSupport(args, returnType, t, gateKeeper)) {
				return handler.handle(args, returnType, t, gateKeeper);
			}
		}

		if (t instanceof BaseException) {
			return Responses.fail((BaseException) t);
		}

		return Responses.fail(Exceptions.bizException(t.getMessage()));
	}
}