package com.study.SpringSecurity.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = 2)
public class TestAspect {

    //@Pointcut("execution(String com.study.SpringSecurity.service.TestService.aop*(..))")    //aop*(..) 매개변수가 0개이상
    @Pointcut("execution(* com.study.SpringSecurity.service.*.aop*(..))")    //aop*(..) 매개변수가 0개이상
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("전처리");
        Object result = proceedingJoinPoint.proceed();  // 핵심기능 호출
        System.out.println("후처리");

        return result;
    }
}
