package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${bookservice.valuetorefresh}")
    private String valueToRefresh;

    @GetMapping("/hello")
    public String helloMessage() {
        return "Hello from Andrew";
    }

    @GetMapping("/refreshed-value")
    public String getValueToRefresh() {
        return valueToRefresh;
    }
}
