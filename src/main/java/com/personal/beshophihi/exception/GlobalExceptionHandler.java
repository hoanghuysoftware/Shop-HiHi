package com.personal.beshophihi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<ExceptionMessage> handleApiException() {
        return ResponseEntity
                .status(404)
                .body(ExceptionMessage.builder()
                        .code(404)
                        .message("Not Found Entity")
                        .build());
    }
}
