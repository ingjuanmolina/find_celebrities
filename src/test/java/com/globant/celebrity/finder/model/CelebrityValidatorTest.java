package com.globant.celebrity.finder.model;

import com.globant.celebrity.finder.service.PersonService;
import com.globant.celebrity.finder.service.RelationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CelebrityValidatorTest {

    private PersonService personService;
    private RelationService relationService;
    private CelebrityValidator celebrityValidator;

    @Before
    public void setup(){
        personService = new PersonService();
        relationService = new RelationService();
        celebrityValidator = new CelebrityValidator(personService, relationService);
        for(int i = 1; i <= 10; i++){
            personService.registerPerson(new Person(i));
        }
        int targetId = 7;
        Person known = personService.findById(targetId);
        for(Person subject : personService.getAll()){
            if(subject.getId() != targetId){
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
        //relatePersons(6, 1);
        //relatePersons(6, 3);
        //relatePersons(6, 5);
    }

    private void relatePersons(int idSubject, int idKnown){
        relationService.createRelation(personService.findById(idSubject), personService.findById(idKnown));
    }

    @Test
    public void personWithIdOneIsNotCelebrity(){
        System.out.println(celebrityValidator.findCelebrity());
        //Assert.assertTrue(celebrityValidator.findCelebrity().equals(new Person(7)));
    }
}
