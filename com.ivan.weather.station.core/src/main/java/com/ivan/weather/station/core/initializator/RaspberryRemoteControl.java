package com.ivan.weather.station.core.initializator;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.ivan.weather.station.core.constants.Constants;
import com.ivan.weather.station.core.domain.model.StartRaspberryRequest;

@Component
public class RaspberryRemoteControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaspberryRemoteControl.class);

    private final WebClient webClient;

    @Autowired
    public RaspberryRemoteControl(WebClient webClient) {
        this.webClient = webClient;
    }

    public void startRaspberry(String raspberryRoute) {
        webClient.post()
                 .uri(getRaspberryFullRoute(raspberryRoute, Constants.RASPBERRY_ACTION_START_PATH))
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(new StartRaspberryRequest(getLocalIpAddress())))
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();
    }

    private String getLocalIpAddress() {
        try {
            return InetAddress.getLocalHost()
                              .getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage(), e);
            return "";
        }
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
