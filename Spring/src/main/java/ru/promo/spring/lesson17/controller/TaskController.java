package ru.promo.spring.lesson17.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.promo.spring.lesson17.dao.TaskDao;
import ru.promo.spring.lesson17.entity.TaskEntity;
import ru.promo.spring.lesson17.entity.TaskType;
import ru.promo.spring.lesson17.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public void create(@RequestBody TaskEntity taskEntity) {
        taskService.create(taskEntity);
    }

    @GetMapping("/tasks/{id}")
    public TaskEntity getById(@PathVariable("id") Long id) {
        return taskService.getById(id);
    }

    @GetMapping("/tasks")
    public List<TaskEntity> getByType(@RequestParam("type") TaskType taskType) {
        return taskService.getByType(taskType);
    }

    @PostMapping("/v2/tasks")
    public void create() {
        taskService.create();
    }

}
