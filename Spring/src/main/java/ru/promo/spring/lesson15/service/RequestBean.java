package ru.promo.spring.lesson15.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@Component
@RequestScope
public class RequestBean {

    private static int count = 0;

    public RequestBean() {
        log.info("RequestBean создан. Count = {}", ++count);
    }

    public void method() {
        log.info("RequestBean method");
    }
}
