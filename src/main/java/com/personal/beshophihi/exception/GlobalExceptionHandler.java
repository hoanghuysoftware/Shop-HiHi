package com.personal.beshophihi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<ExceptionMessage> handleEntityNotFoundException() {
        return ResponseEntity
                .status(404)
                .body(ExceptionMessage.builder()
                        .code(404)
                        .message("Not Found Entity")
                        .build());
    }

    @ExceptionHandler(ExistsEntityException.class)
    public ResponseEntity<ExceptionMessage> handleExistsEntityException() {
        return ResponseEntity
                .status(409)
                .body(ExceptionMessage.builder()
                        .code(409)
                        .message("Entity already exists.")
                        .build());
    }
}
