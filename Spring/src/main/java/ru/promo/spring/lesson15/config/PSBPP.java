package ru.promo.spring.lesson15.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.promo.spring.lesson15.annotation.PS;

@Component
public class PSBPP implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            field.setAccessible(true);
            PS annotation = field.getAnnotation(PS.class);
            field.set(bean, annotation.value());
        }, field -> field.isAnnotationPresent(PS.class));
        return bean;
    }
}
