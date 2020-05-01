package com.app.books.config;

import com.app.books.utils.ValidationUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * RequestParamsCheck注解入参校验切面处理类
 */
@Aspect
@Component
public class InputParamsAspect {


    //@Pointcut("execution(* com.app.books.controller..*.*(..))")
    @Before("execution(* com.app.books.controller..*.*(..))")
    public void cutRequestParams(JoinPoint point) {
        Object[] args = point.getArgs();
        for (Object object : args) {
            ValidationUtils.validate(object);
        }
    }

   /* *//**
     * 执行方法前统一执行参数校验
     *//*
    @Before("cutRequestParams()")
    public void checkRequestParams(JoinPoint point) {
    }
*/
}
