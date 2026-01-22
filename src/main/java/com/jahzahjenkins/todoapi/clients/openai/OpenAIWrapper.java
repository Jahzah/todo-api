package com.jahzahjenkins.todoapi.clients.openai;

import org.springframework.stereotype.Component;

import com.openai.client.OpenAIClient;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionUserMessageParam;

@Component
public class OpenAIWrapper {
    private final OpenAIClient client;

    public OpenAIWrapper(OpenAIClient client) { this.client = client; }

    public String callChat(String prompt) {
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
            .model("gpt-3.5-turbo")
            .addMessage(ChatCompletionUserMessageParam.builder()
                .content(ChatCompletionUserMessageParam.Content.ofText(prompt))
                .build())
            .build();
        
        return client.chat().completions().create(params)
            .choices().get(0).message().content()
            .orElseThrow(() -> new RuntimeException("No content in AI response"));
    }
}

