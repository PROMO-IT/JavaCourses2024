package ru.promo.lesson5;

public class Lesson5 {

    private static final DeveloperTask developerTask =
            new DeveloperTask("Create db scheme", "http://giturl");

    private static final Employee developer = new Employee(1, "Ivan", "email@gmail.com");

    private static final Employee qa = new Employee(2, "Jane", "test@gmail.com");

    private static final QATask qaTask = new QATask("Test functions");

    private static final Sprint sprint = new Sprint();

    public static void main(String[] args) {
    }

    public static void interfaceExample() {
        Closable[] closables = new Closable[3];
        closables[0] = qaTask;
        closables[1] = developerTask;
        closables[2] = sprint;
        for (Closable closer : closables) {
            closer.close();
        }
        System.out.println(developer);
    }

    public static void polymorphismExample() {
        sprint.close();

        Task[] tasks = new Task[2];
        tasks[0] = developerTask;
        tasks[1] = qaTask;

        for (Task tsk : tasks) {
            tsk.addTask(developer);
        }
    }

    public static void extendsExample() {
        developerTask.addTask(developer);
        System.out.println(developerTask);

        qaTask.addTask(qa);
        qaTask.reportBug("Major");
        qaTask.close();

        System.out.println(qaTask);
    }
}
