package ru.promo.spring.lesson17.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.spring.lesson17.dao.TaskDao;
import ru.promo.spring.lesson17.entity.TaskEntity;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskDao taskDao;

    @PostMapping("/tasks")
    public void create(@RequestBody TaskEntity taskEntity) {
        taskDao.create(taskEntity);
    }

    @GetMapping("/tasks/{id}")
    public TaskEntity getById(@PathVariable("id") Long id) {
        return taskDao.getById(id);
    }
}
