package com.hchoaf.springtodo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException {
    private Long todoId;

    public TodoNotFoundException(Long todoId) {
        super(String.format("todo with id %s not found.", todoId));
        this.todoId = todoId;
    }
}
