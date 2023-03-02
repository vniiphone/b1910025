package com.travel.b1910025.payload.response;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// CustomExceptionHandler.java
// This handler adds custom logic when dealing with exceptions created by MethodArgumentNotValid 
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Create a map to store the response body and timestamp information
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        
        // Stores a map of field errors with the associated error message
        Map<String, String> errors = new LinkedHashMap<>();

        // Collects the field errors associated with ex and stores in the errors map
        ex.getBindingResult().getFieldErrors()
                .stream()
                .forEach(x -> {
                    errors.put(x.getField(), x.getDefaultMessage());
                });
        // Adds errors map to the responseBody
        responseBody.put("errors", errors);
        
        // Returns a ResponseEntity with the headers, body and status code
        return new ResponseEntity<>(responseBody, headers, status);
    }

}

