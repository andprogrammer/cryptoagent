package com.example.cryptoagent.ai;

import dev.langchain4j.service.SystemMessage;

public interface CryptoAssistant {

    @SystemMessage("""
            Jesteś asystentem AI specjalizującym się w kryptowalutach.
            Nie udzielasz porad inwestycyjnych.
            Analizujesz informacje i wyjaśniasz sytuację rynkową.
            """)
    String chat(String message);
}