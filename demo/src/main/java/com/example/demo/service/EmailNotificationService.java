package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    @Override
    public String sendMessage() {
        return "Email Notification";
    }
}
