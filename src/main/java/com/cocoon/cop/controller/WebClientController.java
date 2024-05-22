package com.cocoon.cop.controller;

import com.cocoon.cop.dto.SteamGameDataDto;
import com.cocoon.cop.service.SteamDataResetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WebClientController {

    private final SteamDataResetService steamDataResetService;

    @PostMapping("/api/steam/reset")
    public ResponseEntity<String> steamApiSetRequest() {
        try {
            steamDataResetService.steamDataReset();
        } catch (Exception e) {
            return ResponseEntity.ok("runtime error" + e.getMessage());
        }

        return ResponseEntity.ok("reset successfully");
    }

    @GetMapping("/api/steam/check")
    public Mono<List<SteamGameDataDto>> clientRequest() {


        URI uri = URI.create("http://localhost:5000");

        WebClient webClient = WebClient.builder()
                .baseUrl(uri.toString())
                .build();

        log.info("여기 들어옴 ?");

        return webClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/api")
                                .path("/steam")
                                .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<SteamGameDataDto>>() {

                });

    }

}
