package com.hchoaf.springtodo.controller;

import com.hchoaf.springtodo.dto.TodoDto;
import com.hchoaf.springtodo.entity.Todo;
import com.hchoaf.springtodo.exception.TodoNotFoundException;
import com.hchoaf.springtodo.service.TodoService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;

    @ExceptionHandler
    public ResponseEntity<String> handleTodoNotFoundException(TodoNotFoundException todoNotFoundException) {
        return ResponseEntity.status(404).body(todoNotFoundException.getMessage());
    }

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo() {
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto) {
        TodoDto updatedTodoDto = todoService.updateTodo(todoId, todoDto.getTitle(), todoDto.getDescription(), todoDto.isCompleted());
        return new ResponseEntity<>(updatedTodoDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodoById(todoId);
        return new ResponseEntity<>("Todo Deleted", HttpStatus.OK);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.completeTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.incompleteTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
}
