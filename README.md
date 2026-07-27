# cryptoagent

Please remember to add the following environment variable:

OPENROUTER_API_KEY=sk-or-v1-345345345***

Example -> edit configuration


curl -X POST http://localhost:8080/chat -H "Content-Type: application/json" -d "{\"message\":\"Czy warto kupić BTC?\"}"



User
|
v
CryptoAssistant
|
v
LLM
|
| "potrzebuję aktualnej ceny BTC"
v
CoinPriceTool
|
v
CoinGecko API
|
v
{"bitcoin":{"usd":65023}}
|
v
LLM tworzy analizę
|
v
Response


✅ LangChain4j tool calling działa
✅ Spring injection działa
✅ WebClient działa
✅ CoinGecko działa
✅ Agent potrafi pobrać dane z zewnętrznego źródła




                  +----------------+
                  |  OpenRouter    |
                  |  LLM           |
                  +-------+--------+
                          |
                          |
                    Tool decision
                          |
                          v
                  +---------------+
                  | CoinPriceTool |
                  +-------+-------+
                          |
                          v
                  +---------------+
                  | CoinGecko API |
                  +---------------+
                          |
                          v
                  BTC:
                  $65,014
                  Market Cap:
                  $1.304T
                  24h:
                  +0.97%
