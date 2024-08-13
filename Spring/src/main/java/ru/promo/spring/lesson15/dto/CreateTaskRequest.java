package ru.promo.spring.lesson15.dto;

import lombok.Data;

@Data
public class CreateTaskRequest {

    private String description;

    private Long authorId;

    private Long executorId;
}
