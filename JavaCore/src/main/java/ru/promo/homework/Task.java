package ru.promo.homework;

import java.util.UUID;

public class Task {
    private UUID id;
    private String name;

    public Task(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
