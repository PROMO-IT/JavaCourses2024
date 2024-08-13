package ru.promo.spring.lesson15.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.promo.spring.lesson15.domain.Employee;
import ru.promo.spring.lesson15.domain.Task;
import ru.promo.spring.lesson15.dto.CreateTaskRequest;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final NotificationService notificationService;

    private final Map<Long, Employee> employees = Map.of(
            1L, Employee.builder().id(1L).name("Вася").email("Email1").phone("8-999-999-99-01").build(),
            2L, Employee.builder().id(2L).name("Петя").email("Email2").phone("8-999-999-99-02").build()
    );

    private static long NEXT_TASK_ID = 1L;

    public Task create(CreateTaskRequest request) {
        Employee executor = employees.get(request.getExecutorId());
        Task task = Task.builder()
                .id(NEXT_TASK_ID++)
                .author(employees.get(request.getAuthorId()))
                .executor(executor)
                .description(request.getDescription())
                .build();
        notificationService.notify(executor, "У вас есть новая задача: id = %s".formatted(task.getId()));
        return task;
    }
}
