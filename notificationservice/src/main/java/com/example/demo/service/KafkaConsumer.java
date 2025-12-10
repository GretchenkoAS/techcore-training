package com.example.demo.service;

import com.example.demo.event.BookCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final NotificationService notificationService;

    public KafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "book_events", groupId = "notificationservice")
    public void handle(BookCreatedEvent event) {
        notificationService.processNotificationAsync(event);
    }
}
