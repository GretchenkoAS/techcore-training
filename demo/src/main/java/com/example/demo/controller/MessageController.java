package com.example.demo.controller;

import com.example.demo.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/message")
    public String showMessage() {
        return service.getMessage();
    }
}
