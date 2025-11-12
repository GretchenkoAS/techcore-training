package com.example.demo;

import com.example.demo.domen.Book;
import com.example.demo.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testCreateBook() {
        // Given
        String name = "The Lord of the Rings";
        String author = "Tolkien";
        BookDto dto = new BookDto(name, author);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<BookDto> request = new HttpEntity<>(dto, headers);

        // When
        ResponseEntity<Book> response = testRestTemplate.postForEntity(
                "/books", request, Book.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(name, response.getBody().getName());
        assertEquals(author, response.getBody().getAuthor());
    }
}
