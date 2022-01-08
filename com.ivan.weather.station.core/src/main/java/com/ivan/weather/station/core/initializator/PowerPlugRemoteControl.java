package com.ivan.weather.station.core.initializator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PowerPlugRemoteControl {

    private final WebClient webClient;

    @Autowired
    public PowerPlugRemoteControl(WebClient webClient) {
        this.webClient = webClient;
    }

    public void turnOnPlug(String powerPlugRoute) {
        webClient.post()
                 .uri("http://" + powerPlugRoute + "/relay/0?turn=on")
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();
    }

    public void turnOffPlug(String powerPlugRoute) {
        webClient.post()
                 .uri("http://" + powerPlugRoute + "/relay/0?turn=off")
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();
    }
}
