package ru.promo.lesson6;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Lesson6 {

    public static void main(String[] args) {
        Object[] objects = {1, 2, "3"};
        for (Object o : objects) {
            if (o instanceof Integer i) {
                System.out.println(i + " is Integer");
            } else if (o instanceof String s) {
                System.out.println(s + " is String");
            }
        }

        Task<TaskType> task1 = new Task<>();
        task1.setId(1);
        task1.setTitle("Title 1");
        task1.setDescription("Description 1");
        task1.setTaskType(TaskType.BUG);

        Task<TaskType> task2 = new Task<>();
        task2.setId(2);
        task2.setTitle("Title 2");
        task2.setDescription("Description 2");
        task2.setTaskType(TaskType.FEATURE);

        printUrgency(task2);

        Attachment<String> attachment1 = new Attachment<>(AttachmentType.PICTURE, "https://picture.png");
        Attachment<File> attachment2 = new Attachment<>(AttachmentType.VIDEO, new File("/usr/video.avi"));
        Attachment<ComplexData> attachment3 = new Attachment<>(AttachmentType.COMPLEX, new ComplexData("123", "456"));

        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.remove(2);
        System.out.println(strings);
        System.out.println(strings.contains("d"));

        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println("Strings[1] = " + strings.get(1));

        var integers = List.of(1, 2, 3, 4);
        var integers2 = Arrays.asList(1, 2, 3);
        var integers3 = Collections.singletonList(1);

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(set);
        var integers1 = Set.of(1, 2, 3);
        System.out.println(integers1);

        Queue<String> queue = new PriorityQueue<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        System.out.println(map);
        System.out.println(map.containsKey("3"));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + " , value = " + entry.getValue());
        }

        List<Task<TaskType>> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        System.out.println(tasks);

        List<Integer> integers4 = new ArrayList<>();
        integers4.add(1);
        integers4.add(2);
        integers4.add(4);
        integers4.add(3);
        System.out.println(integers4);
        Collections.sort(integers4);
        System.out.println(integers4);

        Collections.sort(tasks);
        System.out.println(tasks);
    }

    private static <T extends Task<TaskType>> void printUrgency(T task) {
        var taskType = task.getTaskType();
        switch (taskType) {
            case BUG -> System.out.println("High");
            case FEATURE -> System.out.println("Medium");
            default -> System.out.println("Not implemented");
        }
    }
}
