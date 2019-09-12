package com.globant.celebrity.finder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PersonNotFoundException.class})
    public ResponseEntity<String> handlePersonNotFoundException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
