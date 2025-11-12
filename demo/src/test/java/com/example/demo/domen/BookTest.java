package com.example.demo.domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("The Lord of the Rings", "Tolkien");
    }

    @Test
    void testSetNewName() {
        //Given
        String newName = "The Silmarillion";
        String author = "Tolkien";
        assertEquals("The Lord of the Rings", book.getName());
        assertEquals(author, book.getAuthor());

        //When
        book.setName(newName);

        //Then
        assertEquals(newName, book.getName());
        assertEquals(author, book.getAuthor());
    }
}
