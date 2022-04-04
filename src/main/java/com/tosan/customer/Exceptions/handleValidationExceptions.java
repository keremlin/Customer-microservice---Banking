package com.tosan.customer.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
@Slf4j
public class handleValidationExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions2(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            log.error(fieldName + " field " + errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(NinIsExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNinIsExists(NinIsExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NinNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNinNotFound(NinNotFoundException ex) {
        return ex.getMessage();
    }
}
    
