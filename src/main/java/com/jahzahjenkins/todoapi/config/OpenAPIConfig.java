package com.jahzahjenkins.todoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI todoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Todo API")
                        .description("AI-Powered Todo Management System")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Jahzah Jenkins")
                                .email("jjenkins2343@gmail.com")));
    }
}