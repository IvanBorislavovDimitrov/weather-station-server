package com.ivan.weather.station.core.env;

import org.springframework.stereotype.Component;

@Component
public class EnvironmentGetter {

    private static final String HOSTNAME = "HOSTNAME";

    private static final String HOSTNAME_DEFAULT_VALUE = "192.168.1.6";

    public String getLocalHostname() {
        return getOrDefault(HOSTNAME, HOSTNAME_DEFAULT_VALUE);
    }

    @SuppressWarnings("unchecked")
    private <T> T getOrDefault(String name, T defaultValue) {
        String value = System.getenv(name);
        if (value == null) {
            return defaultValue;
        }
        if (defaultValue instanceof Boolean) {
            return (T) Boolean.valueOf(value);
        } else if (defaultValue instanceof String) {
            return (T) value;
        } else if (defaultValue instanceof Integer) {
            return (T) Integer.valueOf(value);
        } else if (defaultValue instanceof Double) {
            return (T) Double.valueOf(value);
        } else if (defaultValue instanceof Long) {
            return (T) Long.valueOf(value);
        }
        return (T) value;
    }

}
