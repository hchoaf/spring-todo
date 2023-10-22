package com.hchoaf.springtodo.service;

import com.hchoaf.springtodo.dto.TodoDto;
import com.hchoaf.springtodo.entity.Todo;
import com.hchoaf.springtodo.exception.TodoNotFoundException;
import com.hchoaf.springtodo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoService {

    private TodoRepository todoRepository;
    private ModelMapper mapper;
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = mapper.map(todoDto, Todo.class);
        todoRepository.save(todo);
        return mapper.map(todo, TodoDto.class);
    }

    public TodoDto getTodo(Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));
        return mapper.map(savedTodo, TodoDto.class);
    }

    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(todo -> mapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    public void deleteTodoById(Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));
        todoRepository.delete(savedTodo);
    }

    public TodoDto updateTodo(Long todoId, String title, String description, Boolean isCompleted) {
        Todo savedTodo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));
        savedTodo.setTitle(title);
        savedTodo.setDescription(description);
        savedTodo.setCompleted(isCompleted);
        return mapper.map(todoRepository.save(savedTodo), TodoDto.class);
    }

    public TodoDto completeTodo(Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));
        savedTodo.setCompleted(true);
        return mapper.map(todoRepository.save(savedTodo), TodoDto.class);
    }

    public TodoDto incompleteTodo(Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));
        savedTodo.setCompleted(false);
        return mapper.map(todoRepository.save(savedTodo), TodoDto.class);
    }




}
