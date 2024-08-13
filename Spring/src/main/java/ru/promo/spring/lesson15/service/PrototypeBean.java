package ru.promo.spring.lesson15.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrototypeBean {

    private static int count = 0;

    public PrototypeBean() {
        log.info("Prototype Bean создан. Count = {}", ++count);
    }

    public void method() {
        log.info("Prototype bean method");
    }
}
