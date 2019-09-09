package com.globant.celebrity.finder.endpoint;

import com.globant.celebrity.finder.model.CelebrityValidator;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonEndpoint {

    private PersonService personService;

    @Autowired
    public PersonEndpoint(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll(){
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        Person registered = personService.registerPerson(person);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id){
        Person found = personService.findById(id);
        return new ResponseEntity<Person>(found, HttpStatus.OK);
    }

    @GetMapping("/relations/id/{id}")
    public Set<Person> getRelations(@PathVariable int id){
        return personService.findById(id).getPersonSet();
    }

    @GetMapping("/find-celebrity")
    public ResponseEntity<Person> isCelebrity(){
        CelebrityValidator celebrityValidator = new CelebrityValidator(personService, null);
        Person person = celebrityValidator.findCelebrity();
        return new ResponseEntity<>(person, HttpStatus.ACCEPTED);
    }
}