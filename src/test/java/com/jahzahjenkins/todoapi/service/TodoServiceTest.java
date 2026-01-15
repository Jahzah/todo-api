package com.jahzahjenkins.todoapi.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import com.jahzahjenkins.todoapi.repository.TodoRepository;
import com.jahzahjenkins.todoapi.model.Todo;
import com.jahzahjenkins.todoapi.exception.TodoNotFoundException;

public class TodoServiceTest {
	
	@Mock
    private TodoRepository todoRepository;
    
    @InjectMocks
    private TodoService todoService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetTodoById_Success() {
        // Arrange
        Long todoId = 1L;
        Todo mockTodo = new Todo();
        mockTodo.setId(todoId);
        mockTodo.setTitle("Test Todo");
        
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(mockTodo));
        
        // Act
        Todo result = todoService.getTodoById(todoId);
        
        // Assert
        assertNotNull(result);
        assertEquals("Test Todo", result.getTitle());
        verify(todoRepository, times(1)).findById(todoId);
    }
    
    @Test
    public void testGetTodoById_NotFound() {
        // Arrange
        Long todoId = 999L;
        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(TodoNotFoundException.class, () -> {
            todoService.getTodoById(todoId);
        });
        
        verify(todoRepository, times(1)).findById(todoId);
    }
    
    @Test
    public void testCreateTodo_Success() {
        // Arrange
        String title = "New Todo";
        String description = "Description";
        Todo savedTodo = new Todo();
        savedTodo.setId(1L);
        savedTodo.setTitle(title);
        savedTodo.setDescription(description);
        
        when(todoRepository.save(any(Todo.class))).thenReturn(savedTodo);
        
        // Act
        Todo result = todoService.createTodo(title, description);
        
        // Assert
        assertNotNull(result);
        assertEquals(title, result.getTitle());
        assertEquals(description, result.getDescription());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }
    
    @Test
    public void testUpdateTodo_Success() {
        // Arrange
        Long todoId = 1L;
        Todo existingTodo = new Todo();
        existingTodo.setId(todoId);
        existingTodo.setTitle("Old Title");
        
        when(todoRepository.findById(todoId)).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(existingTodo);
        
        // Act
        Todo result = todoService.updateTodo(todoId, "New Title", null, true);
        
        // Assert
        assertEquals("New Title", result.getTitle());
        assertTrue(result.isCompleted());
        verify(todoRepository, times(1)).findById(todoId);
        verify(todoRepository, times(1)).save(any(Todo.class));
    }
    
    @Test
    public void testDeleteTodo_Success() {
        // Arrange
        Long todoId = 1L;
        when(todoRepository.existsById(todoId)).thenReturn(true);
        
        // Act
        todoService.deleteTodo(todoId);
        
        // Assert
        verify(todoRepository, times(1)).existsById(todoId);
        verify(todoRepository, times(1)).deleteById(todoId);
    }
}
