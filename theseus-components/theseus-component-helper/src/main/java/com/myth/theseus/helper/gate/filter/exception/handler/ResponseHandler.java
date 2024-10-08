package com.myth.theseus.helper.gate.filter.exception.handler;


import com.myth.theseus.helper.gate.GateKeeper;

public interface ResponseHandler {
    boolean isSupport(Object[] args, Class<?> returnType, Throwable t, GateKeeper gateKeeper);
    Object handle(Object[] args, Object result, Throwable t, GateKeeper gateKeeper);
}
