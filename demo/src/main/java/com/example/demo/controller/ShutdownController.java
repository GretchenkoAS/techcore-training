package com.example.demo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {

    private final ApplicationContext context;

    public ShutdownController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/shutdown")
    public void shutdown() {
        SpringApplication.exit(context, () -> 0);
    }
}
