package com.example.demo.controller;

import com.example.demo.domen.Book;
import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetBook() throws Exception {
        // Given
        String name = "The Lord of the Rings";
        String author = "Tolkien";
        Book book = new Book(name, author);
        book.setId(1L);

        when(service.findById(1L)).thenReturn(book);

        // When and Then
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));
    }

    @Test
    void testCreateBookPositive() throws Exception {
        // Given
        String name = "The Lord of the Rings";
        String author = "Tolkien";
        BookDto dto = new BookDto(name, author);

        Book savedBook = new Book(name, author);
        savedBook.setId(1L);

        when(service.createBook(Mockito.any(BookDto.class))).thenReturn(savedBook);

        // When and then
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));
    }

    @Test
    void testCreateBookNegative() throws Exception {
        // Given
        BookDto dto = new BookDto(null, null);

        // When and then
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
