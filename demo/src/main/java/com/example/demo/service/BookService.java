package com.example.demo.service;

import com.example.demo.domen.Book;
import com.example.demo.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    public Book createBook(BookDto dto) {
        return new Book("1", dto.getName(), dto.getAuthor());
    }
}
