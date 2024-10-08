package com.myth.theseus.helper.gate.filter.wrapper;


import com.myth.theseus.helper.gate.GateKeeper;
import com.myth.theseus.helper.gate.filter.GateFilter;
import org.aspectj.lang.ProceedingJoinPoint;

public class GateFilterWrapper {
	private final GateFilter filter;
	private final GateFilterWrapper next;

	public GateFilterWrapper(GateFilter filter, GateFilterWrapper next) {
		this.filter = filter;
		this.next = next;
	}

	public Object around(ProceedingJoinPoint point, GateKeeper gateKeeper) throws Throwable {
		if (next == null) {
			return point.proceed();
		}

		return warpAround(point, gateKeeper);
	}

	private Object warpAround(ProceedingJoinPoint point, GateKeeper gateKeeper) throws Throwable {
		// 先包装内部操作，再执行当前操作
		ProceedingJoinPoint inner = new GateFilterProceedingJoinPoint(point) {
			@Override
			public Object proceed() throws Throwable {
				return next.around(point, gateKeeper);
			}
		};

		return filter.around(inner, gateKeeper);
	}
}