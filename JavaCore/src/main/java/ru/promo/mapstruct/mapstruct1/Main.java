package ru.promo.mapstruct.mapstruct1;

public class Main {

    public static void main(String[] args) {
        var employee = new Employee("Гоша");


        var task = new Task(1, "CREATE", "description!!!", employee);

        var taskAnswer = TaskMapper.INSTANCE.toTaskAnswer(task);

        System.out.println(taskAnswer);
    }
}
