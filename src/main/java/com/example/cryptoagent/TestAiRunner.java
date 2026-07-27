package com.example.cryptoagent;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestAiRunner implements CommandLineRunner {

    private final ChatModel chatModel;

    public TestAiRunner(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public void run(String... args) {

        String response = chatModel.chat(
                "Wyjaśnij krótko czym jest Bitcoin."
        );

        System.out.println("========== AI RESPONSE ==========");
        System.out.println(response);
        System.out.println("=================================");
    }
}