package com.example.demo.service;

import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository repository;

    @Value("${app.greeting}")
    private String greeting;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public String getMessageFromProperties() {
        return greeting;
    }

    public String getMessageFromDb() {
        return repository.loadMessage();
    }
}
