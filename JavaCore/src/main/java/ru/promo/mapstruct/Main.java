package ru.promo.mapstruct;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        var task = new Task(1, "CREATED", "!!!", "Игорь");

        var taskAnswer = new TaskAnswer(task.getNumber(), task.getStatus(), task.getDescription(),
                task.getEmployee(), LocalDateTime.now(), LocalDateTime.now());

    }
}
