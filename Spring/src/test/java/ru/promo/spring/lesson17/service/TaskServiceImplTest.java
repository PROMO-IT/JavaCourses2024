package ru.promo.spring.lesson17.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.promo.spring.lesson17.dao.TaskDao;
import ru.promo.spring.lesson17.entity.TaskEntity;
import ru.promo.spring.lesson17.entity.TaskType;
import ru.promo.spring.lesson17.exception.TaskNotFoundException;
import ru.promo.spring.lesson17.repository.TaskRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    TaskDao taskDao;

    TaskServiceImpl taskService;

    @BeforeEach
    public void beforeEach() {
        taskDao = mock(TaskDao.class);
        TaskRepository taskRepository = mock(TaskRepository.class);
        taskService = new TaskServiceImpl(taskDao, taskRepository);
    }

    @Test
    void givenTask_whenCreate_thenSaveInDb() {
        TaskEntity taskEntity = TaskEntity.builder()
                .title("Title")
                .description("Description")
                .type(TaskType.FEATURE)
                .build();

        taskService.create(taskEntity);

        verify(taskDao).create(taskEntity);
    }

    @Test
    void givenTaskEntities_whenGetById_thenReturnTask() {
        TaskEntity taskEntity = TaskEntity.builder()
                .id(1L)
                .title("Title")
                .description("Description")
                .type(TaskType.FEATURE)
                .build();
        Mockito.when(taskDao.getById(1L)).thenReturn(taskEntity);

        TaskEntity actual = taskService.getById(1L);

        assertThat(actual).isEqualTo(taskEntity);
    }

    @Test
    void givenTaskEntities_whenGetByIdNotExistingTask_thenThrownException() {

        assertThatThrownBy(() -> taskService.getById(100L))
                .isExactlyInstanceOf(TaskNotFoundException.class)
                .hasMessage("Task with id 100 not found");
    }
}
