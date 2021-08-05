package io.javabrains.springbootconfig.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    //*eveyrwhere root.anypkg*.anyclass*.anymethd*with n numer of args
    @Pointcut(value = "execution(* io.javabrains.springbootconfig.*.*.*(..) )")
    public void myPointCut(){

    }

    @Around("myPointCut()")
    public Object aroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object [] args = joinPoint.getArgs();
        LOGGER.info("method invoke : "+ methodName + "class  : "+ className
                + "args : " + new ObjectMapper().writeValueAsString(args));

        Object object = joinPoint.proceed();

        LOGGER.info("method call finished : "+ methodName + "class  : "+ className
                + "response : " + new ObjectMapper().writeValueAsString(object));

        return object;
    }
}
