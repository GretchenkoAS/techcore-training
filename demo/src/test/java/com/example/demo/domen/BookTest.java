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
        String newName = "The Silmarillion";
        String author = "Tolkien";
        book.setName(newName);
        assertEquals(newName, book.getName());
        assertEquals(author, book.getAuthor());
    }
}
