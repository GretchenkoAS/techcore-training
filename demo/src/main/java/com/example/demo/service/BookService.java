package com.example.demo.service;

import com.example.demo.domen.Book;
import com.example.demo.dto.BookDto;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book createBook(BookDto dto) {
        Book book = new Book(dto.getName(), dto.getAuthor());
        repository.save(book);
        return book;
    }

    @Transactional
    public Book updateBook(Long id, BookDto dto) {
        Optional<Book> optionalBook = repository.findById(id);
        Book book = optionalBook.orElseThrow(() -> new BookNotFoundException(id));
        book.setAuthor(dto.getAuthor());
        book.setName(dto.getName());
        return book;
    }

    public Book findById(Long id) {
        Optional<Book> optionalBook = repository.findById(id);
        return optionalBook.orElseThrow(() -> new BookNotFoundException(id));
    }
}
