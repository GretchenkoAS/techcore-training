package com.example.demo.controller;

import com.example.demo.domen.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") Long id) {
        // пока возвращаем заглушку
        return "Book with ID: " + id;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchBooks(@RequestParam(required = false) String title) {
        if (title != null) {
            return ResponseEntity.ok("Received title: " + title);
        } else {
            return ResponseEntity.ok("No title parameter provided");
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        System.out.println(book);
        return ResponseEntity.ok(book);
    }
}
