package com.example.cryptoagent.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    ChatModel chatModel() {

        String apiKey = System.getenv("OPENROUTER_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "Missing OPENROUTER_API_KEY environment variable"
            );
        }

        System.out.println("MODEL CONNECTED");

        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .baseUrl("https://openrouter.ai/api/v1")
                .modelName("openai/gpt-oss-20b:free")
                .build();
    }
}