package ru.promo.spring.lesson15.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import ru.promo.spring.lesson15.service.EmailNotificationService;

@Component
public class NotificationBFPP implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        BeanDefinition smsNotificationService = beanFactory.getBeanDefinition("smsNotificationService");
//        smsNotificationService.setBeanClassName(EmailNotificationService.class.getName());
    }
}
