package com.example.demo.service;

import com.example.demo.event.BookCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @KafkaListener(topics = "book_events", groupId = "notificationservice")
    public void handle(BookCreatedEvent event) {
        System.out.println("!!!!!!!!!!!!!!!!!: " + event);
    }
}
