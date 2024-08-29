package ru.promo.spring.lesson17.service;

import ru.promo.spring.lesson17.entity.TaskEntity;
import ru.promo.spring.lesson17.entity.TaskType;

import java.util.List;

public interface TaskService {
    void create(TaskEntity task);

    TaskEntity getById(Long id);

    List<TaskEntity> getByType(TaskType taskType);
}
