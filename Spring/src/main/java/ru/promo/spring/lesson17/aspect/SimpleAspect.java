package ru.promo.spring.lesson17.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import ru.promo.spring.lesson17.annotation.LoggingAnnotation;

@Slf4j
@Aspect
@Component
public class SimpleAspect {

//    @Before("within(ru.promo.spring.lesson17.controller.*) && execution(public * getBy*(..))")
//    public void before(JoinPoint joinPoint) {
//        log.info("Before");
//    }

    @Before("@annotation(ru.promo.spring.lesson17.annotation.LoggingAnnotation)")
    public void before(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        var annotation = signature.getMethod().getAnnotation(LoggingAnnotation.class);
        var value = annotation.value();
        log.info("before, value = '{}'", value);
    }

    @After("@annotation(ru.promo.spring.lesson17.annotation.LoggingAnnotation)")
    public void after(JoinPoint joinPoint) {
        log.info("after");
    }

    @AfterReturning("@annotation(ru.promo.spring.lesson17.annotation.LoggingAnnotation)")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("afterReturning");
    }

    @AfterThrowing("@annotation(ru.promo.spring.lesson17.annotation.LoggingAnnotation)")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("afterThrowing");
    }

    @Around("@annotation(ru.promo.spring.lesson17.annotation.LoggingAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around start");
        var watch = new StopWatch();
        watch.start();
        var result = joinPoint.proceed();
        watch.stop();
        log.info("around end, time = {}", watch.getTotalTimeMillis());
        return result;
    }
}
