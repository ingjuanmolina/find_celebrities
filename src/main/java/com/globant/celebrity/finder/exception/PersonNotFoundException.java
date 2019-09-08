package com.globant.celebrity.finder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(){
        super();
    }

    public PersonNotFoundException(String message){
        super(message);
    }
}
