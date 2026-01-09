package com.jahzahjenkins.todoapi.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;


@Entity
@Table(name = "todos")
public class Todo {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String title;
	
	@Column(length = 1000)
    private String description;
    
    @Column(nullable = false)
    private boolean completed = false;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Todo() {
    	this.createdAt = LocalDateTime.now();
    	this.updatedAt = LocalDateTime.now();
        
    }

    public Todo(String title, String description) {
    	this();
        this.title = title;
        this.description = description;
    }
    
    @PreUpdate
    protected void onUpdate() {
    	this.updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
