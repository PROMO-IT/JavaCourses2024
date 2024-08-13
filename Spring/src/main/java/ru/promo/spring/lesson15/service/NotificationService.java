package ru.promo.spring.lesson15.service;

import ru.promo.spring.lesson15.domain.Employee;

public interface NotificationService {

    void notify(Employee employee, String text);
}
