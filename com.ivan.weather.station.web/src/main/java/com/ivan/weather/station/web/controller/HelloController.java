package com.ivan.weather.station.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken() {
        return ResponseEntity.ok()
                             .build();
    }

}
