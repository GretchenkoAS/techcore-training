package com.example.demo.service;

import com.example.demo.event.BookCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    public KafkaConsumer(NotificationService notificationService, ObjectMapper objectMapper) {
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "book_events", groupId = "notificationservice")
    public void handle(String payload) throws JsonProcessingException {
        BookCreatedEvent event =
                objectMapper.readValue(payload, BookCreatedEvent.class);
        notificationService.processNotificationAsync(event);
    }
}
