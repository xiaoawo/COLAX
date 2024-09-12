package com.github.colax.catchlog;


public interface ResponseHandlerI {
    public Object handle(Class returnType, String errCode, String errMsg);
}
