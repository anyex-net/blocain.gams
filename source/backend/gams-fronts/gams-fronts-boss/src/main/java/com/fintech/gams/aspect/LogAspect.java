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

import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.system.entity.UserLog;
import com.fintech.gams.system.service.UserLogService;
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
    private GlobalProperies properies;
    
    @Autowired(required = false)
    private UserLogService  userLogService;
    
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
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            ApiOperation api = method.getAnnotation(ApiOperation.class);
            HttpServletRequest request = ServletsUtils.getRequest();
            UserPrincipal principal = OnLineUserUtils.getPrincipal();
            UserLog userLog;
            if (null != principal)
            {
                userLog = new UserLog(principal.getId());
                userLog.setUserName(principal.getTrueName());
            }
            else
            {
                userLog = new UserLog(-100000000000L);
                userLog.setUserName("匿名");
            }
            userLog.setIpAddr(NetworkUtils.getIpAddr(request));
            userLog.setUrl(request.getRequestURI());
            if (null != api)
            {
                userLog.setContent(api.value());
            }
            else
            {
                userLog.setContent(method.getName());
            }
            userLog.setRunTime(time);
            userLog.setCreateDate(System.currentTimeMillis());
            userLogService.insert(userLog);
        }
        catch (Exception e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
