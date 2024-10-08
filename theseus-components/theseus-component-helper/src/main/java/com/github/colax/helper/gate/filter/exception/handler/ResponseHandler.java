package com.github.colax.helper.gate.filter.exception.handler;


import com.github.colax.helper.gate.GateKeeper;

public interface ResponseHandler {
    boolean isSupport(Object[] args, Class<?> returnType, Throwable t, GateKeeper gateKeeper);
    Object handle(Object[] args, Object result, Throwable t, GateKeeper gateKeeper);
}
