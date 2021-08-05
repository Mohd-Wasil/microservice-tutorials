package io.javabrains.springbootconfig.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Aspect
@Component
public class CustomTransactionAnnotationAspect {

    @Pointcut("@annotation(io.javabrains.springbootconfig.aspects.CustomTransaction)")
    public void p1(){}

    @Around("p1()")
    public void transaction(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("i am starting transaction");

        joinPoint.proceed();

        System.out.println("transactionc completed!!!");

    }

}
