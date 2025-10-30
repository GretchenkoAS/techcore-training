package com.example.demo.controller;

import com.example.demo.domen.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/book")
    public Book getNewBook() {
        return new Book("The Name Of The Wind", "Patrick Rothfuss");
    }
}
