package com.silence.interceptor;

import com.silence.utils.IPUtil;
import com.silence.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author silence
 * @since 2024/6/16 01:11
 **/
@Slf4j
@Aspect
@Component
public class RequestLoggingAspect {

    @Pointcut("execution(* com.silence.controller.*.*(..))")
    public void myPointcut() {
    }

    @Before("myPointcut()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = RequestUtil.getRequest();
        log.info("Request URL: {}", request.getRequestURL());
        log.info("Request Method: {}", request.getMethod());
        log.info("IP Address: {}", IPUtil.getClientIpAddr(request));
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("Request Param: {}", arg);
        }
    }

    @AfterReturning(pointcut = "myPointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("Exiting method: {}", joinPoint.getSignature().getName());
        log.info("Response result: {}", result);
    }
}
