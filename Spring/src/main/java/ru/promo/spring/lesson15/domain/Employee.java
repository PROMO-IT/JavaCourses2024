package ru.promo.spring.lesson15.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private Long id;

    private String name;

    private String email;

    private String phone;
}
