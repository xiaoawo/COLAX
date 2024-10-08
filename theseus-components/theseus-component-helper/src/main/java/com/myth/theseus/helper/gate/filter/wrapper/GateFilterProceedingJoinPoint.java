package com.myth.theseus.helper.gate.filter.wrapper;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;

public abstract class GateFilterProceedingJoinPoint implements ProceedingJoinPoint {

	private final ProceedingJoinPoint point;

	public GateFilterProceedingJoinPoint(ProceedingJoinPoint point) {
		this.point = point;
	}


	@Override
	public void set$AroundClosure(AroundClosure aroundClosure) {
		point.set$AroundClosure(aroundClosure);
	}

	@Override
	public Object proceed(Object[] objects) throws Throwable {
		return point.proceed(objects);
	}

	@Override
	public String toShortString() {
		return point.toShortString();
	}

	@Override
	public String toLongString() {
		return point.toLongString();
	}

	@Override
	public Object getThis() {
		return point.getThis();
	}

	@Override
	public Object getTarget() {
		return point.getTarget();
	}

	@Override
	public Object[] getArgs() {
		return point.getArgs();
	}

	@Override
	public Signature getSignature() {
		return point.getSignature();
	}

	@Override
	public SourceLocation getSourceLocation() {
		return point.getSourceLocation();
	}

	@Override
	public String getKind() {
		return point.getKind();
	}

	@Override
	public StaticPart getStaticPart() {
		return point.getStaticPart();
	}
}