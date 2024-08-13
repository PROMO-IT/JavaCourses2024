package ru.promo.spring.lesson15.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.promo.spring.lesson15.service.*;

@Configuration
public class AppConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    @Bean
    public NotificationService notificationService(RequestBean requestBean, SessionBean sessionBean) {
        return new EmailNotificationService(requestBean, sessionBean) {
            @Override
            public PrototypeBean getPrototypeBean() {
                return prototypeBean();
            }
        };
    }
}
