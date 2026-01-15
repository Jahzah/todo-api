package com.jahzahjenkins.todoapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jahzahjenkins.todoapi.service.TodoService;
import com.jahzahjenkins.todoapi.exception.TodoNotFoundException;
import com.jahzahjenkins.todoapi.model.Todo;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private TodoService todoService;
    
    @Test
    public void testGetTodoById_Success() throws Exception {
        // Arrange
        Long todoId = 1L;
        Todo mockTodo = new Todo();
        mockTodo.setId(todoId);
        mockTodo.setTitle("Test Todo");
        mockTodo.setCompleted(false);
        
        when(todoService.getTodoById(todoId)).thenReturn(mockTodo);
        
        // Act & Assert
        mockMvc.perform(get("/api/todos/{id}", todoId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Test Todo"))
               .andExpect(jsonPath("$.completed").value(false));
    }
    
    @Test
    public void testCreateTodo_Success() throws Exception {
        // Arrange
        Todo createdTodo = new Todo();
        createdTodo.setId(1L);
        createdTodo.setTitle("New Todo");
        createdTodo.setDescription("Description");
        
        when(todoService.createTodo(anyString(), anyString())).thenReturn(createdTodo);
        
        String requestBody = "{\"title\":\"New Todo\",\"description\":\"Description\"}";
        
        // Act & Assert
        mockMvc.perform(post("/api/todos")
               .contentType(MediaType.APPLICATION_JSON)
               .content(requestBody))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.title").value("New Todo"));
    }
    
    @Test
    public void testGetTodoById_NotFound() throws Exception {
        // Arrange
        Long todoId = 999L;
        when(todoService.getTodoById(todoId)).thenThrow(new TodoNotFoundException(todoId));
        
        // Act & Assert
        mockMvc.perform(get("/api/todos/{id}", todoId))
               .andExpect(status().isNotFound());
    }

}
