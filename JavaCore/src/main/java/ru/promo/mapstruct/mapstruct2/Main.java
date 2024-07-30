package ru.promo.mapstruct.mapstruct2;

public class Main {

    public static void main(String[] args) {
        var employee = new Employee("Гоша", 38);
        var task = new Task(1, "CREATE", "description!!!", employee);

        var taskAnswer = TaskMapper.INSTANCE.toTaskAnswer(task);

        System.out.println(taskAnswer);
    }
}
