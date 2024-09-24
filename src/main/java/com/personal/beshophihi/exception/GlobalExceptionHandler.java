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

    @ExceptionHandler(DateException.class)
    public ResponseEntity<ExceptionMessage> handleDateException() {
        return ResponseEntity
                .status(400)
                .body(ExceptionMessage.builder()
                        .code(400)
                        .message("Invalid date input.")
                        .build());
    }

    @ExceptionHandler(InputValidException.class)
    public ResponseEntity<ExceptionMessage> handleInputValidException() {
        return ResponseEntity
                .status(400)
                .body(ExceptionMessage.builder()
                        .code(400)
                        .message("Invalid input.")
                        .build());
    }
}
