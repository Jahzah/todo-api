package com.jahzahjenkins.todoapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jahzahjenkins.todoapi.model.Todo;
import com.jahzahjenkins.todoapi.service.ChatService;
import com.jahzahjenkins.todoapi.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    
    @Autowired
    private TodoService todoService;
    
    @Autowired
    private ChatService chatService;
    
    // GET all todos
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }
    
    // GET single todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }
    
    // CREATE new todo
    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
        Todo created = todoService.createTodo(todo.getTitle(), todo.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    // UPDATE existing todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody Map<String, Object> updates) {
        
        String title = (String) updates.get("title");
        String description = (String) updates.get("description");
        Boolean completed = (Boolean) updates.get("completed");
        
        Todo updated = todoService.updateTodo(id, title, description, completed);
        
        return ResponseEntity.ok(updated);
    }
    
    // DELETE todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
    	todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
    
    // PATCH - Mark as complete
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Todo> completeTodo(@PathVariable Long id) {
        Todo updated = todoService.completeTodo(id);
        
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<Todo>> filterTodos(@RequestParam Boolean completed) {
        List<Todo> todos = todoService.getTodosByCompleted(completed);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Todo>> searchTodos(@RequestParam String keyword) {
        List<Todo> todos = todoService.searchTodosByTitle(keyword);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Todo>> getSortedTodos() {
        List<Todo> todos = todoService.getAllTodosSortedByDate();
        return ResponseEntity.ok(todos);
    }
    
    @GetMapping("/paginated")
    public ResponseEntity<Page<Todo>> getTodosPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Todo> todos = todoService.getTodosPaginated(page, size);
        return ResponseEntity.ok(todos);
    }
    
    @PostMapping("/suggestions")
    public ResponseEntity<String> getTodoSuggestions(@RequestBody String userGoal) {
        String suggestions = chatService.generateTodoSuggestions(userGoal);
        return ResponseEntity.ok(suggestions);
    }
}
