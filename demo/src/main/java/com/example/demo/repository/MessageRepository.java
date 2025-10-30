package com.example.demo.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

    public String loadMessage() {
        return "Message from DB";
    }
}
