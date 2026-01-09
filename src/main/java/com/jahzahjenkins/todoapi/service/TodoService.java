package com.jahzahjenkins.todoapi.service;

import com.jahzahjenkins.todoapi.model.Todo;
import com.jahzahjenkins.todoapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    // Update a todo
    public Optional<Todo> updateTodo(Long id, String title, String description, Boolean completed) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            if (title != null) todo.setTitle(title);
            if (description != null) todo.setDescription(description);
            if (completed != null) todo.setCompleted(completed);
            return Optional.of(todoRepository.save(todo));
        }
        
        return Optional.empty();
    }

    // Delete a todo
    public boolean deleteTodo(Long id) {
    	if (todoRepository.existsById(id)) {
    		todoRepository.deleteById(id);
    		return true;
    	}
        return false;
    }

    // Mark todo as complete
    public Optional<Todo> completeTodo(Long id) {
        return updateTodo(id, null, null, true);
    }
}
