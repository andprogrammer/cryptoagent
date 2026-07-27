package com.example.cryptoagent.config;

import com.example.cryptoagent.ai.CryptoAssistant;
import com.example.cryptoagent.tool.CoinPriceTool;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiServiceConfig {

    @Bean
    public CryptoAssistant cryptoAssistant(
            ChatModel chatModel,
            CoinPriceTool coinPriceTool
    ) {

        return AiServices.builder(CryptoAssistant.class)
                .chatModel(chatModel)
                .tools(coinPriceTool)
                .build();
    }
}