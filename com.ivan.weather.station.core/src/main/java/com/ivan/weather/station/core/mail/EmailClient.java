package com.ivan.weather.station.core.mail;

public interface EmailClient {

    void sendAsync(Email email);
}
