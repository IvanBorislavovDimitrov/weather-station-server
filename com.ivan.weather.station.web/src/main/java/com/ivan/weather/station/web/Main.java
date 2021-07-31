package com.ivan.weather.station.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ivan.weather")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
