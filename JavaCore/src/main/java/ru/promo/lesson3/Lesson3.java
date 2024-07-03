package ru.promo.lesson3;

public class Lesson3 {

    public static void main(String[] args) {
        Employee employee1 = new Employee(1, "Ivan", "email@gmail.com");

        System.out.println(employee1);

        Task task1 = new Task("Выучить ООП");
        Task task2 = new Task("Изучить принципы ООП");

        System.out.println(task1);
        System.out.println(task2);
        System.out.println(Task.taskNumberCount);

        Task task3 = task1;

        System.out.println("Task3 = " + task3);
        System.out.println("Task1 = " + task1);

        task1.addTask(employee1);
        System.out.println("Task3 upd = " + task3);
        System.out.println("Task1 upd = " + task1);

        String status = task1.getStatus();
        System.out.println("Task1 status = " + status);
    }
}
