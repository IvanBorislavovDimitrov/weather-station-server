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

    public void executeAction(String powerPlugRoute, String action) {
        webClient.post()
                 .uri("http://" + powerPlugRoute + "/relay/0?turn=" + action)
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();
    }

}
