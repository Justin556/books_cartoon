package com.app.books.config;

import com.app.books.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * RequestParamsCheck注解入参校验切面处理类
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class InputParamsAspect {


    @Pointcut("execution(* com.app.books.controller..*.*(..))")
    public void cutRequestParams() {
        log.info("=========================");
    }

    /**
     * 执行方法前统一执行参数校验
     */
    @Before("cutRequestParams()")
    public void checkRequestParams(JoinPoint point) {
        Object[] args = point.getArgs();
        for (Object object : args) {
            ValidationUtils.validate(object);
        }
    }

}
