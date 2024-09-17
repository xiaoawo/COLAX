package com.github.colax.helper.gate.filter;

import com.github.colax.helper.gate.GateKeeper;
import org.aspectj.lang.ProceedingJoinPoint;

public interface GateFilter {
	Object around(ProceedingJoinPoint point, GateKeeper gateKeeper) throws Throwable;
}
