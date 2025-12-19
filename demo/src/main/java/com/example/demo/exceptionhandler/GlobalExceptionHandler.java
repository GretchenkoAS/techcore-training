package com.example.demo.exceptionhandler;

import com.example.demo.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException e, WebRequest request) {
        ErrorResponse error = ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage())
                .title("Book Not Found")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
