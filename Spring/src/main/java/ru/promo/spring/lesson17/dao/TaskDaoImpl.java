package ru.promo.spring.lesson17.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.spring.lesson17.entity.TaskEntity;
import ru.promo.spring.lesson17.entity.TaskType;
import ru.promo.spring.lesson17.exception.TaskNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

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
        return task;
    }

    @Override
    public List<TaskEntity> getByType(TaskType taskType) {
        return entityManager.createQuery("select t from TaskEntity t where t.type = :paramType")
                .setParameter("paramType", taskType)
                .getResultList();
    }


}
