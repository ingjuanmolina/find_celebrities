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

    @ExceptionHandler(value = {PersonLocalRepositoryException.class})
    public ResponseEntity<String> handlePersonLocalRepositoryException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalRelationException.class})
    public ResponseEntity<String> handleIllegalRelationException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
