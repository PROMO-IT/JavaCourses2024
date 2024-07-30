package ru.promo.mapstruct.mapstruct0;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        var task = new Task(1, "CREATE", "description!!!", "Гоша");

        var taskAnswer = TaskMapper.INSTANCE.toTaskAnswer(task);

        taskAnswer.setCreateDate(LocalDateTime.now());
        taskAnswer.setUpdateDate(LocalDateTime.now());

        System.out.println(task);
        System.out.println(taskAnswer);
    }
}
