package com.personal.beshophihi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<ExceptionMessage> handleEntityNotFoundException(EntityNotFound e) {
        return ResponseEntity
                .status(404)
                .body(ExceptionMessage.builder()
                        .code(404)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(ExistsEntityException.class)
    public ResponseEntity<ExceptionMessage> handleExistsEntityException(ExistsEntityException e) {
        return ResponseEntity
                .status(409)
                .body(ExceptionMessage.builder()
                        .code(409)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(DateException.class)
    public ResponseEntity<ExceptionMessage> handleDateException(DateException e) {
        return ResponseEntity
                .status(400)
                .body(ExceptionMessage.builder()
                        .code(400)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(InputValidException.class)
    public ResponseEntity<ExceptionMessage> handleInputValidException(InputValidException e) {
        return ResponseEntity
                .status(400)
                .body(ExceptionMessage.builder()
                        .code(400)
                        .message(e.getMessage())
                        .build());
    }
}
