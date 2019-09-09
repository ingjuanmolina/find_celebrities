package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class RelationService {

    private PersonService personService;

    @Autowired
    public RelationService(PersonService personService) {
        this.personService = personService;
    }

    public void establishRelation(int subjectId, int knownId){
        Person subject = personService.findById(subjectId);
        Person known = personService.findById(knownId);
        subject.getPersonSet().add(known);
    }
}
