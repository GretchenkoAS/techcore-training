package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService implements NotificationService{

    @Override
    public String sendMessage() {
        return "SMS Notification";
    }
}
