package ru.promo.spring.lesson15.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.spring.lesson15.domain.Task;
import ru.promo.spring.lesson15.dto.CreateTaskRequest;
import ru.promo.spring.lesson15.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task create(@RequestBody CreateTaskRequest request) {
        return taskService.create(request);
    }
}
