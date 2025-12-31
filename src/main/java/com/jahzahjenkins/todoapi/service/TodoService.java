package com.jahzahjenkins.todoapi.service;

import com.jahzahjenkins.todoapi.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    // In-memory storage (will be replaced with database on Day 3)
    private List<Todo> todos = new ArrayList<>();

    // Create a new todo
    public Todo createTodo(String title, String description) {
        Todo todo = new Todo(title, description);
        todos.add(todo);
        return todo;
    }

    // Get all todos
    public List<Todo> getAllTodos() {
        return new ArrayList<>(todos); // Return copy to prevent modification
    }

    // Get a specific todo by ID
    public Optional<Todo> getTodoById(String id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    // Update a todo
    public Optional<Todo> updateTodo(String id, String title, String description, Boolean completed) {
        Optional<Todo> todoOptional = getTodoById(id);
        
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            if (title != null) todo.setTitle(title);
            if (description != null) todo.setDescription(description);
            if (completed != null) todo.setCompleted(completed);
            return Optional.of(todo);
        }
        
        return Optional.empty();
    }

    // Delete a todo
    public boolean deleteTodo(String id) {
        return todos.removeIf(todo -> todo.getId().equals(id));
    }

    // Mark todo as complete
    public Optional<Todo> completeTodo(String id) {
        return updateTodo(id, null, null, true);
    }
}
