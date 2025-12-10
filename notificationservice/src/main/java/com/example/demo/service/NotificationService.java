package com.example.demo.service;

import com.example.demo.event.BookCreatedEvent;
import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "book_events", groupId = "notificationservice")
    public void handle(BookCreatedEvent event) {
        System.out.println("!!!!!!!!!!!!!!!!!: " + event);
        Notification notification = new Notification(event.id(),
                String.format("New book created: %s (%s)", event.name(), event.author()));
        notificationRepository.save(notification);
        System.out.println("!!!!!!!!!!!!!!!!!: BOOK SAVED");
    }


}
