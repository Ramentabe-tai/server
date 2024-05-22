package com.cocoon.cop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    public WebClient createWebClient() {
        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeStrategies strategies = ExchangeStrategies
                .builder()
                .codecs(clientCodecConfigurer -> clientCodecConfigurer
                        .defaultCodecs()
                        .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper)))
                .build();


        return WebClient.builder()
                .exchangeStrategies(strategies)
                .baseUrl("http://localhost:8080")
                .build();
    }
}
