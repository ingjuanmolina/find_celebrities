package com.globant.celebrity.finder;

import com.globant.celebrity.finder.model.CelebrityValidator;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.service.PersonService;
import com.globant.celebrity.finder.service.RelationServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;

public class App {

    private PersonService personService = new PersonService(null);
    @Autowired
    private RelationServiceLocal relationServiceLocal;

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
        Person known = personService.findById(7);
        for(Person subject : personService.getAll()){
            if(subject.getId() != 7){
                relationServiceLocal.createRelation(subject, known);
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
        relationServiceLocal.createRelation(personService.findById(idSubject), personService.findById(idKnown));
    }

    private void findCelebrity(){
        CelebrityValidator celebrityValidator = new CelebrityValidator(personService, relationServiceLocal);
        System.out.println("Celebrity: " +  celebrityValidator.findCelebrity());
    }
}
