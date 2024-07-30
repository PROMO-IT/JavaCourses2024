package ru.promo.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.promo.stream.TaskType.EPIC;

public class Compute {

    private static final Employee employee1 = new Employee(1, "Jane", "test1@mail");
    private static final Employee employee2 = new Employee(2, "Ivan", "test2@mail");
    private static final Employee employee3 = new Employee(3, "Jack", "test3@mail");
    private static final Employee employee4 = new Employee(4, "Jane", "test4@mail");

    private static final List<Task<TaskType>> tasks = List.of(
            new Task<>(1, "Task1", "Desc1", TaskType.BUG),
            new Task<>(3, "Task2", "Desc3", EPIC),
            new Task<>(4, "Task2", "Desc4", TaskType.FEATURE),
            new Task<>(5, "Task5", "Desc5", TaskType.FEATURE),
            new Task<>(2, "Task1", "Desc2", EPIC));

    private static final List<Task<TaskType>> tasksEmp = List.of(
            new Task<>(1, "Task1", "Desc1", employee4, TaskType.BUG),
            new Task<>(2, "Task2", "Desc2", employee2, EPIC),
            new Task<>(3, "Task3", "Desc3", employee1, EPIC),
            new Task<>(4, "Task4", "Desc4", employee3, TaskType.FEATURE),
            new Task<>(5, "Task5", "Desc5", employee2, TaskType.FEATURE));

    private static final List<Task<Attachment<List<Integer>>>> tasksTimes = List.of(
            new Task<>(1, "Task1", "Desc1", new Attachment<>(AttachmentType.AUDIO, List.of(2, 3))),
            new Task<>(2, "Task2", "Desc2", new Attachment<>(AttachmentType.COMPLEX, List.of(3, 3))),
            new Task<>(3, "Task3", "Desc3", new Attachment<>(AttachmentType.PICTURE, List.of(4, 3))),
            new Task<>(4, "Task4", "Desc4", new Attachment<>(AttachmentType.AUDIO, List.of(5, 3))),
            new Task<>(5, "Task5", "Desc5", new Attachment<>(AttachmentType.AUDIO, List.of(2, 5))));

    public static void main(String[] args) {
        Computable sumComputor = Integer::sum;
        Computable divComputor = (a, b) -> a / b;
        Computable difComputor = (a, b) -> a - b;
        Computable multComputor = (a, b) -> a * b;

        int sum = compute(sumComputor, 2, 2);

        filter();
        mapping();
        reducing();
        skipLimit();
        flatMap();
        sorting();
        grouping();
    }

    public static void filter() {
        Task<TaskType> firstTask = tasks.stream()
                .filter(task -> task.getTaskType().equals(EPIC))
                .findFirst().orElseThrow();
    }

    public static void mapping() {
        List<Employee> collect = tasksEmp.stream()
                .map(Task::getEmployee)
                .toList();
    }

    public static void reducing() {
        Integer reduce = Stream.of(1, 2, 3, 4)
                .reduce(0, (acc, i) -> acc + i);

        String titles = tasks.stream()
                .reduce("", (acc, task) -> acc + " " + task.getTitle(), String::concat);
    }

    public static void skipLimit() {
        Stream.of(1, 2, 3, 4, 4, 5, 5)
                .skip(2)
                .limit(2)
                .forEach(i -> System.out.print(i + " "));
    }

    public static void flatMap() {
        var timeSum = tasksTimes.stream()
                .flatMap(task -> task.getTaskType().getContent().stream())
                .reduce(Integer::sum);
    }

    public static void sorting() {
        tasks.stream()
                .sorted(Comparator.comparing(Task::getId))
                .forEach(System.out::println);

        tasksEmp.stream()
                .map(Task::getEmployee)
                .distinct()
                .sorted(Comparator.comparing(Employee::getName).thenComparing(Employee::getEmail))
                .forEach(System.out::println);
    }

    public static void grouping() {
        Map<TaskType, List<Task<TaskType>>> group = tasks.stream()
                .collect(Collectors.groupingBy(Task::getTaskType));
    }

    public static int compute(Computable computable, int a, int b) {
        return computable.compute(a, b);
    }
}
