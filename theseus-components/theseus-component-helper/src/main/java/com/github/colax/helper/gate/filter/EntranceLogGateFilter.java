package com.github.colax.helper.gate.filter;


import com.github.colax.helper.gate.GateKeeper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntranceLogGateFilter implements GateFilter {

	@Override
	public Object around(ProceedingJoinPoint point, GateKeeper gateKeeper) throws Throwable {
		long startTime = System.currentTimeMillis();
		long endTime;
		try {
			Object result = point.proceed();
			endTime = System.currentTimeMillis();
			log.info("cost time: {} ms", endTime - startTime);
			return result;
		} finally {
			endTime = System.currentTimeMillis();
			log.info("cost time: {} ms", endTime - startTime);
		}
	}
}