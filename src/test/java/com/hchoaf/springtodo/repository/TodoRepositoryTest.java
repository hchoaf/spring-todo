package com.hchoaf.springtodo.repository;

import com.hchoaf.springtodo.entity.Todo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TodoRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoRepository todoRepository;
    private static String title = "Todo 1";
    private static String description = "Some description";
    private static boolean isCompleted = false;
    @Test
    @Transactional
    public void todoSearchTest() {
        Todo todo = new Todo(title, description, isCompleted);
        entityManager.persist(todo);
        entityManager.flush();
        Todo actual = todoRepository.findById(todo.getId()).get();
        System.out.println(todoRepository.findById(21L).get());
        assertEquals(todo, actual);
    }
    @Test
    @Transactional
    public void updateTodoTest() {
    }

    @Test
    @Transactional
    public void deleteTodoTest() {

    }

    @Test
    @Transactional
    public void throwTodoNotFoundExceptionTest() {

    }

}