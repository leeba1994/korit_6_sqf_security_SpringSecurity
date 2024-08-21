package com.study.SpringSecurity.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.security.CodeSigner;

@Aspect
@Component
@Order(value = 1)
public class TestAspect2 {

    //@Pointcut("execution(String com.study.SpringSecurity.service.TestService.aop*(..))")    //aop*(..) 매개변수가 0개이상
    @Pointcut("@annotation(com.study.SpringSecurity.aspect.annotation.Test2Aop)")    //aop*(..) 매개변수가 0개이상
    private void pointCut() {}


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

       //Signature signature = proceedingJoinPoint.getSignature();
       CodeSignature signature = (CodeSignature) proceedingJoinPoint.getSignature();
        System.out.println(signature.getName());
        System.out.println(signature.getDeclaringTypeName());

        Object[] args = proceedingJoinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();

        for(int i = 0; i < args.length; i++) {
            System.out.println(paramNames[i] + ": " + args[i]);
        };
        System.out.println("전처리2");
        Object result = proceedingJoinPoint.proceed();  // 핵심기능 호출
        System.out.println("후처리2");

        return result;
    }
}
