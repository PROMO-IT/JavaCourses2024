package ru.promo.lesson4;

public class Service {

    public static void main(String[] args) {
        var task0 = new Task("Task0");

        var task1 = new Task("Task1");

        task0.subTask = task1;

        task1.subTask = task0;

        setExecutor(null, "Promo It");
        System.out.println(task1);
    }

    public static void setExecutor(Task task, String executor) throws RuntimeException {
        if (task == null) {
            throw new IllegalArgumentException("Задача не может быть null");
        }
        if (task.executor == null) {
            task.executor = executor;
        }
        var subTask = task.subTask;
        if (subTask == null) {
            return;
        }
        setExecutor(task.subTask, executor);
    }

}
