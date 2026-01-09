package com.jahzahjenkins.todoapi.repository;

import com.jahzahjenkins.todoapi.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TodoRepository extends JpaRepository<Todo, Long>{
	

}
