package com.globant.celebrity.finder.endpoint;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;
import com.globant.celebrity.finder.service.RelationServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/relations")
public class RelationEndpoint {

    private RelationServiceLocal relationServiceLocal;

    @Autowired
    public RelationEndpoint(RelationServiceLocal relationServiceLocal){
        this.relationServiceLocal = relationServiceLocal;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Set<Person>> getAllRelations(@PathVariable int id){
        Person person = new Person(id);
        return new ResponseEntity<Set<Person>>(
                relationServiceLocal.getPersonRelations(person), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Relation> createRelation(@RequestBody Relation relation){
        relationServiceLocal.createRelation(relation);
        return new ResponseEntity<>(relation, HttpStatus.CREATED);
    }
}
