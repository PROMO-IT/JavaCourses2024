package ru.promo.spring.lesson14;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @GetMapping("/demo")
    public String demo() {
        log.info("demo method");
        return "demo method";
    }

    @GetMapping("/task/{id}")
    public Task getTask(@RequestParam(value = "status", required = false) String status,
                        @PathVariable("id") Long id) {
        log.info("Get task method with status: {}", status);
        log.info("Get task method with id: {}", id);

        Task task = new Task();
        task.setId(1L);
        task.setName("task1");
        task.setDescription("description1");

        return task;
    }

    @PostMapping("/task")
    public Task createTask(@RequestBody Task task) {
        log.info("Create task: {}", task);
        return task;
    }

    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        log.info("Delete task with id: {}", id);
    }
}
