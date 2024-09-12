package com.github.colax.catchlog;

import com.github.colax.helper.ApplicationContextHelper;

public class ResponseHandlerFactory {

    public static ResponseHandlerI get(){
        if(ApplicationContextHelper.getBean(ResponseHandlerI.class) != null){
            return ApplicationContextHelper.getBean(ResponseHandlerI.class);
        }
        return new DefaultResponseHandler();
    }
}
