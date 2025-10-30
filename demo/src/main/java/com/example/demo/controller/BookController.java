package com.example.demo.controller;

import com.example.demo.domen.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/book")
    public Book getNewBook() {
        return new Book("1", "The Name Of The Wind", "Patrick Rothfuss");
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable("id") Long id) {
        // пока возвращаем заглушку
        return "Book with ID: " + id;
    }
}
