package ru.promo.lesson4;

public class Task {

    String title;

    String executor;

    Task subTask;

    public Task(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", executor='" + executor + '\'' +
                '}';
    }
}
