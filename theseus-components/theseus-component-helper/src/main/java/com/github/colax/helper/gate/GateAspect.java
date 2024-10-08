package com.github.colax.helper.gate;

import com.github.colax.helper.gate.context.GateCarrier;
import com.github.colax.helper.gate.filter.wrapper.GateFilterWrapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Order
@Aspect
public class GateAspect {
	private final GateFilterWrapper filterWrapper;
	public GateAspect(GateFilterWrapper filterWrapper) {
		this.filterWrapper = filterWrapper;
	}


	@Around("@annotation(gateKeeper)")
	public Object around(ProceedingJoinPoint point, GateKeeper gateKeeper) throws Throwable {
		try {
			GateCarrier.init();
			return filterWrapper.around(point, gateKeeper);
		} finally {
			GateCarrier.clear();
		}
	}
}