package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Value("${app.greeting}")
    private String greeting;

    public String getMessage() {
        return greeting;
    }
}
