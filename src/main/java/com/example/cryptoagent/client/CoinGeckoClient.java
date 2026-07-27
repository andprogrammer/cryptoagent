package com.example.cryptoagent.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CoinGeckoClient {

    private final WebClient webClient;

    public CoinGeckoClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://api.coingecko.com/api/v3")
                .build();
    }

    public String getBitcoinPrice() {

        return webClient.get()
//                .uri("/simple/price?ids=bitcoin&vs_currencies=usd")
                .uri("/simple/price?ids=bitcoin&vs_currencies=usd&include_24hr_change=true&include_market_cap=true")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}