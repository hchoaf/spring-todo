package com.hchoaf.springtodo.repository;

import com.hchoaf.springtodo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository <Todo, Long>{

}
