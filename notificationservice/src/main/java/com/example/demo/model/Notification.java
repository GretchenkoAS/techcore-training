package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    private final Long bookId;
    private final String message;
    private final LocalDateTime createdAt;

    public Notification(Long bookId, String message) {
        this.bookId = bookId;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }
}