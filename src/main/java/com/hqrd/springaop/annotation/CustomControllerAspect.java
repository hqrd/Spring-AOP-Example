package com.hqrd.springaop.annotation;

import com.hqrd.springaop.exception.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author hqrd
 */
@Aspect
@Component
public class CustomControllerAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.hqrd.springaop.annotation.CustomController)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    @Around("@annotation(com.hqrd.springaop.annotation.CustomController)")
    public Object paramNotOksAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {

        LOGGER.debug("Entering in Method :  " + joinPoint.getSignature().getName());
        LOGGER.debug("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.debug("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
        LOGGER.debug("Target class : " + joinPoint.getTarget().getClass().getName());

        paramNotOks(joinPoint.getArgs());

        Object proceed = joinPoint.proceed();

        checkResponse(proceed);

        LOGGER.debug("RESULT : " + String.valueOf(proceed));
        return proceed;
    }

    private void paramNotOks(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof String && paramNotOk((String) arg))
                throw new CustomException("KO-500-Invalid parameters");
            else if (arg instanceof Collection && paramNotOk((Collection) arg))
                throw new CustomException("KO-500-Invalid parameters");
            else if (paramNotOk(arg))
                throw new CustomException("KO-500-Invalid parameters");
        }
    }

    private boolean paramNotOk(String arg) {
        return arg == null || arg.isEmpty();
    }

    private boolean paramNotOk(Collection arg) {
        return arg == null || arg.isEmpty();
    }

    private boolean paramNotOk(Object arg) {
        return arg == null;
    }

    private void checkResponse(Object response) {
        if (response == null) {
            throw new CustomException("KO-500-Unknown internal error");
        }
    }

}

