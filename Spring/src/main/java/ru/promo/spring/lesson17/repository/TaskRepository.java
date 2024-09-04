package ru.promo.spring.lesson17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.spring.lesson17.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
