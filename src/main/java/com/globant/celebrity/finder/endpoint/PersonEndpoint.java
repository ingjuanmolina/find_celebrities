package com.globant.celebrity.finder.endpoint;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonEndpoint {

    private PersonService personService;

    @Autowired
    public PersonEndpoint(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/test")
    public String test(){
        personService.registerPerson(new Person(1));
        personService.registerPerson(new Person(2));
        personService.registerPerson(new Person(3));
        String output = "";
        for(Person p : personService.getAll()){
            output += p + "\n";
        }
        return output;
    }

    @GetMapping
    public ResponseEntity<Set<Person>> getAll(){
        return new ResponseEntity<Set<Person>>(personService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        personService.registerPerson(person);
        return new ResponseEntity<Person>(person, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id){
        return new ResponseEntity<Person>(personService.findById(id), HttpStatus.OK);
    }
}