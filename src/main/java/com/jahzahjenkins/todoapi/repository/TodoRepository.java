package com.jahzahjenkins.todoapi.repository;

import com.jahzahjenkins.todoapi.model.Todo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoRepository extends JpaRepository<Todo, Long>{
	
	List<Todo> findByCompleted(Boolean completed);
	
	List<Todo> findByTitleContainingIgnoreCase(String keyword);
	
	List<Todo> findAllByOrderByCreatedAtDesc();
	
	Page<Todo> findAll(Pageable pageable);
}
