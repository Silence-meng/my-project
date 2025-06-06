package com.silence.aspect;

import cn.hutool.core.date.StopWatch;
import cn.hutool.json.JSONUtil;
import com.silence.enetity.RequestInfo;
import com.silence.utils.RequestHolder;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Around("myPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        //如果参数有HttpRequest,ServletResponse，直接移除，不打印这些
        List<Object> paramList = Stream.of(joinPoint.getArgs())
                .filter(args -> !(args instanceof ServletRequest))
                .filter(args -> !(args instanceof ServletResponse))
                .collect(Collectors.toList());
        String printParamStr = paramList.size() == 1 ? JSONUtil.toJsonStr(paramList.get(0)) : JSONUtil.toJsonStr(paramList);
        RequestInfo requestInfo = RequestHolder.get();
        String userHeaderStr = JSONUtil.toJsonStr(requestInfo);
        log.info("[{}][{}]【base:{}】【request:{}】", method, uri, userHeaderStr, printParamStr);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        long cost = stopWatch.getTotalTimeMillis();
        String printResultStr = JSONUtil.toJsonStr(result);
        log.info("[{}]【response:{}】[cost:{}ms]", uri, printResultStr, cost);

        return result;
    }
}
