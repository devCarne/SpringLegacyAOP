package stu.kms.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j
public class LogAdvice {

    @Before("execution(* stu.kms.service.SampleService*.*(..))")
    public void logBefore() {
        log.info("================================");
    }

    @Before("execution(* stu.kms.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("str1 : " + str1);
        log.info("str2 : " + str2);
    }

    @AfterThrowing(pointcut = "execution(* stu.kms.service.SampleService*.*(..))", throwing = "e")
    public void logException(Exception e) {
        log.info("Exception Occurred");
        log.info("exception : " + e);
    }

    @Around("execution(* stu.kms.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();

        log.info("Target : " + joinPoint.getTarget());
        log.info("Param : " + Arrays.toString(joinPoint.getArgs()));

        //Invoke method...
        Object result = null;

        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long end = System.currentTimeMillis();

        log.info("Time : " + (end - start));
        log.info(result);

        return result;
    }
}
