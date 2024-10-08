package com.github.colax.helper.gate.filter;

import com.github.colax.helper.gate.GateKeeper;
import com.myth.theseus.util.validation.Validations;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class ValidationGateFilter implements GateFilter {

	@Override
	public Object around(ProceedingJoinPoint point, GateKeeper gateKeeper) throws Throwable {
		Object[] args = point.getArgs();
		if (args == null) {
			return point.proceed();
		}

		for (Object arg : args) {
			Validations.validate(arg);
		}

		return point.proceed();
	}
}