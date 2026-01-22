package com.jahzahjenkins.todoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jahzahjenkins.todoapi.exception.TodoNotFoundException;
import com.jahzahjenkins.todoapi.model.Todo;
import com.jahzahjenkins.todoapi.repository.TodoRepository;

@Service
public class TodoService {
    
	@Autowired
	private TodoRepository todoRepository;

    // Create a new todo
    public Todo createTodo(String title, String description) {
        Todo todo = new Todo(title, description);
        return todoRepository.save(todo);
    }

    // Get all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll(); 
    }

    // Get a specific todo by ID
    public Todo getTodoById(Long id) {
    	return todoRepository.findById(id)
        .orElseThrow(() -> new TodoNotFoundException(id));
    }

    // Update a todo
    public Todo updateTodo(Long id, String title, String description, Boolean completed) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException(id));
        
        if (title != null) todo.setTitle(title);
        if (description != null) todo.setDescription(description);
        if (completed != null) todo.setCompleted(completed);
        
        return todoRepository.save(todo);
    }

    // Delete a todo
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }
        todoRepository.deleteById(id);
    }


    // Mark todo as complete
    public Todo completeTodo(Long id) {
        return updateTodo(id, null, null, true);
    }
    
    public List<Todo> getTodosByCompleted(Boolean completed) {
        return todoRepository.findByCompleted(completed);
    }

    public List<Todo> searchTodosByTitle(String keyword) {
        return todoRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Todo> getAllTodosSortedByDate() {
        return todoRepository.findAllByOrderByCreatedAtDesc();
    }
    
    public Page<Todo> getTodosPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return todoRepository.findAll(pageable);
    }
}
