package com.onlinevoting.OnlineVoting.utility;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

}