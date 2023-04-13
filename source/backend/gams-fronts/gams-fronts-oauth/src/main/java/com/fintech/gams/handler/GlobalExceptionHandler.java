package com.fintech.gams.handler;

import javax.naming.AuthenticationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alipay.sofa.rpc.core.exception.SofaRouteException;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.exception.LimitAccessException;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler
{
    /**
     * 并发限制异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = LimitAccessException.class)
    public JsonMessage handleLimitAccessException(LimitAccessException e)
    {
        return new JsonMessage(401, e.getLocalizedMessage());
    }
    
    /**
    * 业务异常控制
    * @param e
    * @return
    */
    @ExceptionHandler(value = BusinessException.class)
    public JsonMessage handleBusinessException(BusinessException e)
    {
        return new JsonMessage(e.getError(), e.getObject());
    }
    
    /**
     * 安全认证异常控制
     * @param e
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public JsonMessage handleAuthenticationException(AuthenticationException e)
    {
        return new JsonMessage(CommonEnums.ERROR_AUTHER_FAILED);
    }
    
    /**
    * SOFA异常处理
    * @param e
    * @return
    */
    @ExceptionHandler(value = SofaRouteException.class)
    public JsonMessage handleSofaRouteException(SofaRouteException e)
    {
        return new JsonMessage(401, "服务链路异常");
    }
    
    /**
     * 处理500错误
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public JsonMessage handleException(Exception e)
    {
        return new JsonMessage(401, e.getLocalizedMessage());
    }
}
