package com.ivan.weather.station.web.initializator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RaspberryInitializator {

    private final WebClient webClient;

    @Autowired
    public RaspberryInitializator(WebClient webClient) {
        this.webClient = webClient;
    }

    public void startRaspberry(String raspberryRoute) {
        webClient.post()
                .uri(raspberryRoute + "/start")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public void stopRaspberry(String raspberryRoute) {
        webClient.post()
                .uri(raspberryRoute + "/stop")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
