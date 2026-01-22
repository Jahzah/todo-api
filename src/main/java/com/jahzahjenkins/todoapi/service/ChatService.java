package com.jahzahjenkins.todoapi.service;

import org.springframework.stereotype.Service;

import com.jahzahjenkins.todoapi.clients.openai.OpenAIWrapper;

@Service
public class ChatService {
    
    private final OpenAIWrapper openAIWrapper;
    
    public ChatService(OpenAIWrapper openAIWrapper) {
        this.openAIWrapper = openAIWrapper;
    }
    
    public String generateTodoSuggestions(String userInput) {
        try {
            String prompt = "You are a helpful assistant that suggests todo tasks based on user goals. " +
                           "Provide 3-5 specific, actionable todo items. Format as a numbered list. " +
                           "User goal: " + userInput;
            
            return openAIWrapper.callChat(prompt);
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate AI suggestions: " + e.getMessage());
        }
    }
}