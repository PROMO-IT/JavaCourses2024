package ru.promo.spring.lesson17.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.promo.spring.lesson17.exception.TaskNotFoundException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public String notFoundException(TaskNotFoundException exception) {
        return exception.getMessage();
    }
}
