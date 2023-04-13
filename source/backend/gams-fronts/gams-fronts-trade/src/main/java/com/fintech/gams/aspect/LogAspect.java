package com.fintech.gams.aspect;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fintech.gams.account.entity.AccountLog;
import com.fintech.gams.account.service.AccountLogService;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.NetworkUtils;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.ServletsUtils;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAspect
{
    @Autowired(required = false)
    private GlobalProperies   properies;
    
    @Autowired(required = false)
    private AccountLogService accountLogService;
    
    @Pointcut(value = "execution(* com.fintech.gams.controller.*..*(..))")
    public void logPointCut()
    {
    }
    
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 异步保存日志
        if (properies.isOpenLog())
        {
            saveLog(point, BigDecimal.valueOf(time).multiply(BigDecimal.valueOf(0.001)));
        }
        return result;
    }
    
    /**
     * 保存用户操作日志
     *
     * @param joinPoint
     * @param time
     * @throws Throwable
     */
    @Async
    public void saveLog(ProceedingJoinPoint joinPoint, BigDecimal time) throws Throwable
    {
        try
        {
            UserPrincipal principal = OnLineUserUtils.getPrincipal();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            ApiOperation api = method.getAnnotation(ApiOperation.class);
            HttpServletRequest request = ServletsUtils.getRequest();
            AccountLog accountLog;
            if (null != principal)
            {
                accountLog = new AccountLog(principal.getId());
                accountLog.setAccountName(principal.getTrueName());
            }
            else
            {
                accountLog = new AccountLog(-100000000000L);
                accountLog.setAccountName("匿名");
            }
            accountLog.setIpAddr(NetworkUtils.getIpAddr(request));
            accountLog.setUrl(request.getRequestURI());
            if (null != api)
            {
                accountLog.setContent(api.value());
            }
            else
            {
                accountLog.setContent(method.getName());
            }
            accountLog.setRunTime(time);
            accountLog.setCreateDate(System.currentTimeMillis());
            accountLogService.insert(accountLog);
        }
        catch (Exception e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
