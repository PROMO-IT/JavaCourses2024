package ru.promo.spring.lesson15.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@SessionScope
@Component
public class SessionBean {

    private static int count = 0;

    public SessionBean() {
        log.info("SessionBean создан. Count = {}", ++count);
    }

    public void method() {
        log.info("SessionBean method");
    }
}
