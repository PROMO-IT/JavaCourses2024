package ru.promo.spring.lesson15.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.promo.spring.lesson15.annotation.Logging;
import ru.promo.spring.lesson15.annotation.PS;
import ru.promo.spring.lesson15.domain.Employee;

@Slf4j
@RequiredArgsConstructor
public abstract class EmailNotificationService implements NotificationService {

    @PS
    private String ps;

    private final RequestBean requestBean;
    private final SessionBean sessionBean;

    @PostConstruct
    public void init() {
        if (ps.isBlank()) {
            ps = "Продается дом";
        }
    }

    @Logging
    @Override
    public void notify(Employee employee, String text) {
        text = text + " P.S. " + ps;
        log.info("Отправлено письмо: username = {}, message = {}", employee.getName(), text);
        getPrototypeBean().method();
        getPrototypeBean().method();
        requestBean.method();
        requestBean.method();
        sessionBean.method();
        sessionBean.method();
    }

    @PreDestroy
    public void destroy() {
        log.info("EmailNotification Destroy");
    }

    protected abstract PrototypeBean getPrototypeBean();
}
