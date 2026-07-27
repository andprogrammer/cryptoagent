package com.example.cryptoagent.tool;

import com.example.cryptoagent.client.CoinGeckoClient;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class CoinPriceTool {

    private final CoinGeckoClient coinGeckoClient;

    public CoinPriceTool(CoinGeckoClient coinGeckoClient) {
        this.coinGeckoClient = coinGeckoClient;
    }

    @Tool("Gets current Bitcoin price from CoinGecko")
    public String getBitcoinPrice() {

        System.out.println(">>> CoinGecko TOOL CALLED");

        String price = coinGeckoClient.getBitcoinPrice();

        System.out.println(">>> CoinGecko RESPONSE: " + price);

        return price;
    }
}