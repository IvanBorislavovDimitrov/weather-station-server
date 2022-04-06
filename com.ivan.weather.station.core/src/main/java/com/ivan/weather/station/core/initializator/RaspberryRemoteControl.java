package com.ivan.weather.station.core.initializator;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

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
            InetAddress theOneAddress = getInetAddress();
            return theOneAddress.getHostAddress();
        } catch (SocketException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException("No network interface found!");
        }
    }

    private InetAddress getInetAddress() throws SocketException {
        InetAddress theOneAddress;
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface networkInterface : Collections.list(nets)) {
            if (!networkInterface.isLoopback()) {
                theOneAddress = Collections.list(networkInterface.getInetAddresses())
                                           .stream()
                                           .findFirst()
                                           .orElse(null);
                if (theOneAddress != null) {
                    return theOneAddress;
                }
            }
        }
        throw new IllegalArgumentException("No network interface found!");
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
