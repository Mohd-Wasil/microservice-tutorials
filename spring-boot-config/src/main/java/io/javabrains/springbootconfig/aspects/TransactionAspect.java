package io.javabrains.springbootconfig.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

   /* @Pointcut("execution(public * io.javabrains.*.*.*(..)) ")
    public void p1(){
    }*/

    /*  @Pointcut("execution(public * io.javabrains.springbootconfig.services.EmployeeService.*(..)) ")
     public void p1(){
     }*/
    @Pointcut("execution(* io.javabrains.springbootconfig.services.EmployeeService.*(..)) ")
    public void p1() {
    }

    @Around("p1()")
    public void aroundTest(ProceedingJoinPoint joinPoint) {
        System.out.println("before business method - around bhai");

        try {
            Object obj = joinPoint.proceed(); //business class method is invoked withthis api
        } catch (Throwable throwable) {
            System.out.println("after business method execption rollback- around bhai");
        }
        System.out.println("after business method SUCCESS - around bhai");

    }


    /*@Before("p1()")
    public void beginTx(){
        System.out.println("i am begginig transaction..");
    }

    @AfterReturning(value = "p1()", returning = "obj")
    public void commitTx(Object obj){
        System.out.println("transaction committed" + obj.toString());
    }

    @AfterThrowing(value= "p1()" , throwing = "th")
    public void rollback(Throwable th){
        System.out.println("transaction rollback initiated : " + th.getLocalizedMessage());
    }*/
}
