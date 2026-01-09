package com.jahzahjenkins.todoapi.controller;

import com.jahzahjenkins.todoapi.model.Todo;
import com.jahzahjenkins.todoapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    
    @Autowired
    private TodoService todoService;
    
    // GET all todos
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }
    
    // GET single todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        return todo.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    // CREATE new todo
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        String description = request.get("description");
        
        if (title == null || title.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Todo todo = todoService.createTodo(title, description);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }
    
    // UPDATE existing todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        
        String title = (String) updates.get("title");
        String description = (String) updates.get("description");
        Boolean completed = (Boolean) updates.get("completed");
        
        Optional<Todo> updated = todoService.updateTodo(id, title, description, completed);
        
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    // DELETE todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        boolean deleted = todoService.deleteTodo(id);
        
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // PATCH - Mark as complete
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Todo> completeTodo(@PathVariable Long id) {
        Optional<Todo> updated = todoService.completeTodo(id);
        
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
}
