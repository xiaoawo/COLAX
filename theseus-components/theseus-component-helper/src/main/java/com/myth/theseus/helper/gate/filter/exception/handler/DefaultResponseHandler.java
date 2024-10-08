package com.myth.theseus.helper.gate.filter.exception.handler;


import com.myth.theseus.helper.gate.GateKeeper;
import com.myth.theseus.model.exception.BaseException;
import com.myth.theseus.model.page.Page;
import com.myth.theseus.model.request.PageQuery;
import com.myth.theseus.model.response.MultiResponse;
import com.myth.theseus.model.response.PageResponse;
import com.myth.theseus.model.response.Response;
import com.myth.theseus.model.response.SingleResponse;
import com.myth.theseus.util.Responses;
import com.myth.theseus.util.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * ResponseHandler
 *
 * @author Frank Zhang
 * @date 2020-11-10 3:18 PM
 */
@Slf4j
@Component
public class DefaultResponseHandler implements ResponseHandler {

    @Override
    public boolean isSupport(Object[] args, Class<?> returnType, Throwable t, GateKeeper gateKeeper) {
        return Response.class.isAssignableFrom(returnType);
    }

    @Override
    public Object handle(Object[] args, Object result, Throwable t, GateKeeper gateKeeper) {
        // 打印异常信息并处理返回结果
        if (t instanceof BaseException) {
            BaseException error = (BaseException) t;
            log.error(error.toString(), t);
            return buildResponse(args, result, error);
        } else {
            log.error(t.getMessage(), t);
            return buildResponse(args, result, Exceptions.sysException(t.getMessage()));
        }
    }

    private Object buildResponse(Object[] args, Object result, BaseException error) {
        if (result == null) {
            return null;
        }
        if (Objects.equals(result.getClass(), Response.class)) {
            return Responses.fail(error);
        } else if (Objects.equals(result.getClass(), SingleResponse.class)) {
            return Responses.failSingle(error);
        } else if (Objects.equals(result.getClass(), MultiResponse.class)) {
            return Responses.failMulti(error);
        } else if (Objects.equals(result.getClass(), PageResponse.class)) {
            // 寻找Page
            Page page = Page.DEFAULT;
            for (Object arg : args) {
                if (arg instanceof Page) {
                    page = (Page) arg;
                    break;
                } else if (arg instanceof PageQuery) {
                    PageQuery pageQuery = (PageQuery) arg;
                    page = pageQuery.getPage();
                    break;
                }
            }
            return Responses.failPage(error, page);
        }

        return result;
    }
}
