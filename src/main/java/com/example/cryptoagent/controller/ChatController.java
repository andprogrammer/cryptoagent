package com.example.cryptoagent.controller;

import com.example.cryptoagent.ai.CryptoAssistant;
import com.example.cryptoagent.dto.ChatRequest;
import com.example.cryptoagent.dto.ChatResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final CryptoAssistant assistant;

    public ChatController(CryptoAssistant assistant) {
        this.assistant = assistant;
    }

    @PostMapping
    public ChatResponse chat(
            @RequestBody ChatRequest request
    ) {

        System.out.println("START AI REQUEST");

        long start = System.currentTimeMillis();

        String answer = assistant.chat(request.message());

        long end = System.currentTimeMillis();

        System.out.println("AI TIME: " + (end - start) + " ms");

        return new ChatResponse(answer);
    }
}