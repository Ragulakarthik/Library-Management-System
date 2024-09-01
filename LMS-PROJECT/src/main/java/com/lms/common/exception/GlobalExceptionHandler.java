package com.lms.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LmsException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleLmsException(LmsException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", java.time.Instant.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", ex.getMessage());
//        response.put("fieldName", ex.getFieldName());
//        response.put("duplicateValue", ex.getDuplicateValue());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", java.time.Instant.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("Type of error", "Bad Request");
        response.put("message", ex.getMessage());
        response.put("fieldName", ex.getFieldName());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", java.time.Instant.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("Type of error", "Bad Request");
        response.put("Invaid Params", ex.getBindingResult().getFieldErrors().stream().map(error -> {
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("field", error.getField());
            errorDetails.put("defaultMessage", error.getDefaultMessage());
            return errorDetails;
        }).toArray());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Invalid input: " + ex.getValue() + " cannot be converted to type " + ex.getRequiredType().getName());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}