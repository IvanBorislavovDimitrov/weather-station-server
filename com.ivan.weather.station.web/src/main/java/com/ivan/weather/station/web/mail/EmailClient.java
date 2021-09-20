package com.ivan.weather.station.web.mail;

public interface EmailClient {

    void sendAsync(Email email);
}
