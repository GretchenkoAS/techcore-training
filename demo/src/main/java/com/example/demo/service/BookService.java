package com.example.demo.service;

import com.example.demo.domen.Book;
import com.example.demo.dto.BookDto;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    public BookService(BookRepository repository, RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    public Book createBook(BookDto dto) {
        Book book = new Book(dto.getName(), dto.getAuthor());

        List<ServiceInstance> instances
                = discoveryClient.getInstances("notificationservice");

        if (instances == null || instances.isEmpty()) {
            throw new IllegalStateException("No instances of notificationservice found");
        }

        ServiceInstance instance = instances.getFirst();
        String baseUrl = instance.getUri().toString();

        restTemplate.postForObject(baseUrl + "/notify", "New book created: " + book.getName(), String.class);

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

    public void deleteBookById(Long id) {
        repository.deleteById(id);
    }
}
