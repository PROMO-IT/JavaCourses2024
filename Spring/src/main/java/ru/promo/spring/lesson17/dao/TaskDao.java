package ru.promo.spring.lesson17.dao;

import ru.promo.spring.lesson17.entity.TaskEntity;

public interface TaskDao {
    void create(TaskEntity task);

    TaskEntity getById(Long id);
}
