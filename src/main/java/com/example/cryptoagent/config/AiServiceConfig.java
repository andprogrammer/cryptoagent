package com.example.cryptoagent.config;

import com.example.cryptoagent.ai.CryptoAssistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiServiceConfig {

    @Bean
    public CryptoAssistant cryptoAssistant(ChatModel chatModel) {

        return AiServices.builder(CryptoAssistant.class)
                .chatModel(chatModel)
                .build();
    }
}