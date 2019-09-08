package com.globant.celebrity.finder.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/persons")
public class PersonEndpoint {

    @GetMapping
    public String test(){
        return "Persons Endpoint";
    }
}
