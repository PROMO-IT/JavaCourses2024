package ru.promo.spring.lesson17.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.spring.lesson17.entity.TaskEntity;
import ru.promo.spring.lesson17.exception.TaskNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class TaskDaoImpl implements TaskDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(TaskEntity task) {
        task.getAttachments().forEach(attachmentEntity -> attachmentEntity.setTask(task));
        entityManager.persist(task);
    }

    @Override
    public TaskEntity getById(Long id) {
        var task = entityManager.find(TaskEntity.class, id);
        if (task == null) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        return task;
    }
}
