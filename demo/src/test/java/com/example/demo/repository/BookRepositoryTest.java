package com.example.demo.repository;

import com.example.demo.domen.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BookRepositoryTest {

    private BookRepository repository;

    public BookRepositoryTest(@Autowired BookRepository repository) {
        this.repository = repository;
    }

    @Test
    void testSaveAndFindById() {
        // Given
        String name = "The Lord of the Rings";
        String author = "Tolkien";
        Book book = new Book(name, author);

        // When
        repository.save(book);

        // Then
        Optional<Book> foundBookOptional = repository.findById(book.getId());

        assertTrue(foundBookOptional.isPresent());
        Book foundBook = foundBookOptional.get();
        assertEquals(name, foundBook.getName());
        assertEquals(author, foundBook.getAuthor());
    }
}
