package com.example.demo.controller;

import com.example.demo.domen.Book;
import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

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
    public ResponseEntity<Book> createBook(@RequestBody BookDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok(service.createBook(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody BookDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok(service.updateBook(id, dto));
    }
}
