package com.example.demo.service;

import com.example.demo.domen.Book;
import com.example.demo.domen.OutboxEvent;
import com.example.demo.dto.BookDto;
import com.example.demo.event.BookCreatedEvent;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public BookService(BookRepository repository, OutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Book createBook(BookDto dto) throws JsonProcessingException {
        Book book = new Book(dto.getName(), dto.getAuthor());

        repository.save(book);

        BookCreatedEvent event = new BookCreatedEvent(
                book.getId(),
                book.getName(),
                book.getAuthor()
        );

        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateId(book.getId());
        outboxEvent.setEventType("BookCreatedEvent");
        outboxEvent.setPayload(objectMapper.writeValueAsString(event));

        outboxRepository.save(outboxEvent);        return book;
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

    public void deleteBookById(Long id) {
        repository.deleteById(id);
    }
}
