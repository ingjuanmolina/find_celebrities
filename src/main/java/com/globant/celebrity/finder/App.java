package com.globant.celebrity.finder;

import com.globant.celebrity.finder.model.CelebrityValidator;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.service.PersonService;
import com.globant.celebrity.finder.service.RelationService;

import java.util.Random;

public class App {

    private PersonService personService = new PersonService();
    private RelationService relationService = new RelationService();

    public void run(){
        registerPersons();
        setRelations();
        findCelebrity();
    }

    public void registerPersons(){
        for(int i = 1; i <= 10; i++){
            personService.registerPerson(new Person(i));
        }
    }

    public void setRelations(){
        Person known = personService.finbById(7);
        for(Person subject : personService.getAll()){
            if(subject.getId() != 7){
                relationService.createRelation(subject, known);
            }
        }
        relatePersons(1, 2);
        relatePersons(1, 4);
        relatePersons(1, 6);
        relatePersons(2, 1);
        relatePersons(2, 3);
        relatePersons(3, 7);
        relatePersons(4, 1);
        relatePersons(5, 1);
        relatePersons(5, 3);
        relatePersons(5, 7);
        relatePersons(6, 1);
        relatePersons(6, 3);
        relatePersons(6, 5);
    }

    private void relatePersons(int idSubject, int idKnown){
        relationService.createRelation(personService.finbById(idSubject), personService.finbById(idKnown));
    }

    private void findCelebrity(){
        CelebrityValidator celebrityValidator = new CelebrityValidator(personService, relationService);
        System.out.println("Celebrity: " +  celebrityValidator.findCelebrity());
    }
}
