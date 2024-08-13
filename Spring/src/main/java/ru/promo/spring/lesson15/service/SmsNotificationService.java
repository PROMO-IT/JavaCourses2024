package ru.promo.spring.lesson15.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.promo.spring.lesson15.domain.Employee;

@Slf4j
//@Service
public class SmsNotificationService implements NotificationService {

    @Override
    public void notify(Employee employee, String text) {
        log.info("Отправлен смс: username = {}, message = {}", employee.getName(), text);
    }
}
