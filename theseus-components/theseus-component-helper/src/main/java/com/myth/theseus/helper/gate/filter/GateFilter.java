package com.myth.theseus.helper.gate.filter;

import com.myth.theseus.helper.gate.GateKeeper;
import org.aspectj.lang.ProceedingJoinPoint;

public interface GateFilter {
	Object around(ProceedingJoinPoint point, GateKeeper gateKeeper) throws Throwable;
}
