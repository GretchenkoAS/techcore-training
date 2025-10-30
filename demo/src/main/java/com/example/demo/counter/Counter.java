package com.example.demo.counter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Counter {

    private int value = 0;

    public int getValue() {
        return value;
    }

    public void increment() {
        value++;
    }
}