package com.example.demo.controller;

import com.example.demo.domen.Book;
import com.example.demo.dto.BookDto;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final Map<Long, Book> books = new HashMap<>();

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = books.get(id);
        if(book != null) {
            return ResponseEntity.ok(book);
        }
        else {
            throw new BookNotFoundException(id);
        }
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        // Delete logic
        return ResponseEntity.noContent().build();
    }
}
