package ru.promo.spring.lesson15.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {

    private Long id;

    private String description;

    private Employee author;

    private Employee executor;
}
