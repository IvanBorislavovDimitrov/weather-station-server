package com.ivan.weather.station.web.initializator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RaspberryRemoteControl {

    private final WebClient webClient;

    @Autowired
    public RaspberryRemoteControl(WebClient webClient) {
        this.webClient = webClient;
    }

    public void startRaspberry(String raspberryRoute) {
        webClient.post()
                 .uri("http://" + raspberryRoute + ":8080/start")
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();
    }

    public void stopRaspberry(String raspberryRoute) {
        webClient.post()
                 .uri("http://" + raspberryRoute + ":8080/stop")
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();
    }

}
