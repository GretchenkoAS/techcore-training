package com.example.demo.service;

import com.example.demo.event.BookCreatedEvent;
import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Async("notificationServiceExecutor")
    public CompletableFuture<Notification> processNotificationAsync(BookCreatedEvent event) {
        try {
            System.out.println("!!!!!!!!!!!!!!!!!: " + event);
            Notification notification = new Notification(event.id(),
                    String.format("New book created: %s (%s)", event.name(), event.author()));
            notificationRepository.save(notification);
            System.out.println("!!!!!!!!!!!!!!!!!: BOOK SAVED");

            return CompletableFuture.completedFuture(notification);
        } catch (Exception ex) {
            return CompletableFuture.failedFuture(ex);
        }
    }
}
