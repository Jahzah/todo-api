package com.jahzahjenkins.todoapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/")
	public String home() {
		return "Welcome to Todo API!";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello, Spring Boot!";
	}
	
	@GetMapping("/api/status")
	public String status() {
		return "API is running!";
	}
}
