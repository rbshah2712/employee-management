package com.employee.Employee.controllers;

import java.util.Map;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException exception) {
        Map<String, Object> response = Map.of(
                "success", false,
                "status", 401,
                "message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception exception) {
        return errorResponse(HttpStatus.BAD_REQUEST, "Invalid request body.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleInternalServerError(Exception exception) {
        exception.printStackTrace(); 
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    private ResponseEntity<Map<String, Object>> errorResponse(HttpStatus status, String message) {
        Map<String, Object> response = Map.of(
                "success", false,
                "status", status.value(),
                "message", message);

        return ResponseEntity.status(status).body(response);
    }

}