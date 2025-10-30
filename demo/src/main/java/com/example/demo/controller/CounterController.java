package com.example.demo.controller;

import com.example.demo.service.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/test-counter")
    public String testCounter() {
        return counterService.testCounter();
    }
}
