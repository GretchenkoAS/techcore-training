package com.example.demo.service;

import com.example.demo.domen.Book;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("The Lord of the Rings", "Tolkien");
        book.setId(1L);
    }

    @Test
    void testFindByIdPositive() {
        // Given
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(book));

        // When
        Book foundBook = service.findById(id);

        // Then
        assertEquals("The Lord of the Rings", foundBook.getName());
        assertEquals("Tolkien", foundBook.getAuthor());
    }

    @Test
    void testFindByIdException() {
        // Given
        Long invalidId = 2L;
        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        // When and then
        assertThrows(BookNotFoundException.class, () -> service.findById(invalidId));
    }
}
