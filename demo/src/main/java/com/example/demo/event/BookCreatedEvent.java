package com.example.demo.event;

public record BookCreatedEvent(
        Long id,
        String name,
        String author
) {}