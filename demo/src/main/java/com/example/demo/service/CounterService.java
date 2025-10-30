package com.example.demo.service;

import com.example.demo.counter.Counter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    private final ObjectProvider<Counter> counterProvider;

    public CounterService(ObjectProvider<Counter> counterProvider) {
        this.counterProvider = counterProvider;
    }

    public String testCounter() {
        Counter c1 = counterProvider.getObject();
        Counter c2 = counterProvider.getObject();

        c1.increment();
        return "Counter 1: " + c1.getValue() + "\n" + "Counter 2: " + c2.getValue();
    }
}
