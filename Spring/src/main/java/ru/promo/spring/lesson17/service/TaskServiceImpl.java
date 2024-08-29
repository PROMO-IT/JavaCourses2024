package ru.promo.spring.lesson17.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.promo.spring.lesson17.dao.TaskDao;
import ru.promo.spring.lesson17.entity.TaskEntity;
import ru.promo.spring.lesson17.entity.TaskType;
import ru.promo.spring.lesson17.exception.TaskNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;

    @Override
    public void create(TaskEntity task) {
        //some business code
        taskDao.create(task);
        //some business code
    }

    @Override
    public TaskEntity getById(Long id) {
        TaskEntity task = taskDao.getById(id);
        if (task == null) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        return task;
    }

    @Override
    public List<TaskEntity> getByType(TaskType taskType) {
        return taskDao.getByType(taskType);
    }
}
