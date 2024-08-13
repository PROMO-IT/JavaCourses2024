package ru.promo.spring.lesson15.config;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.promo.spring.lesson15.annotation.Logging;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LoggingBPP implements BeanPostProcessor {

    private final Map<String, Class<?>> beanClasses = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(ReflectionUtils.getAllDeclaredMethods(bean.getClass()))
                .filter(method -> method.isAnnotationPresent(Logging.class))
                .findFirst()
                .ifPresent(method -> beanClasses.put(beanName, bean.getClass()));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = beanClasses.get(beanName);
        if (beanClass != null) {
            ProxyFactory factory = new ProxyFactory();
            factory.setTarget(bean);
            factory.addAdvisor(getAdvisor());
            return factory.getProxy();
        }
        return bean;
    }

    private PointcutAdvisor getAdvisor() {
        return new PointcutAdvisor() {
            @Override
            public Pointcut getPointcut() {
                return AnnotationMatchingPointcut.forMethodAnnotation(Logging.class);
            }

            @Override
            public Advice getAdvice() {
                return (MethodInterceptor) invocation -> {
                    Method method = invocation.getMethod();
                    String methodName = method.getDeclaringClass().getSimpleName() + "#" + method.getName();
                    log.info("До вызова метода: method = {}", methodName);
                    Object result = invocation.proceed();
                    log.info("После вызова метода: method = {}", methodName);
                    return result;
                };
            }
        };
    }
}
