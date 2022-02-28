package com.ivan.weather.station.core.initializator;

import com.ivan.weather.station.core.constants.Constants;
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
                 .uri(getRaspberryFullRoute(raspberryRoute, Constants.RASPBERRY_ACTION_START_PATH))
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();
    }

    public void stopRaspberry(String raspberryRoute) {
        webClient.post()
                 .uri(getRaspberryFullRoute(raspberryRoute, Constants.RASPBERRY_ACTION_STOP_PATH))
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();
    }

    private String getRaspberryFullRoute(String raspberryRoute, String raspberryActionStart) {
        return Constants.HTTP_PREFIX + raspberryRoute + Constants.RASPBERRY_DEFAULT_PORT + raspberryActionStart;
    }

}
