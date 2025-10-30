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

    @GetMapping("/message-from-properties")
    public String getMessageFromProperties() {
        return service.getMessageFromProperties();
    }

    @GetMapping("/message-from-db")
    public String getMessageFromDb() {
        return service.getMessageFromDb();
    }
}
