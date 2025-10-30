package com.example.demo.controller;

import com.example.demo.service.ObjectMapperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ObjectMapperController {

    private final ObjectMapperService service;

    public ObjectMapperController(ObjectMapperService service) {
        this.service = service;
    }

    @GetMapping("/json")
    public String getJson() {
        Map<String, Object> data = Map.of(
                "message", "Hello from ObjectMapper bean!",
                "status", "success"
        );
        return service.toJson(data);
    }
}
