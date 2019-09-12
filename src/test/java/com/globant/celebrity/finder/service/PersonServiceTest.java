package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;
import com.globant.celebrity.finder.repository.PersonRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;

public class PersonServiceTest {


    private PersonRepository personRepository;
    private PersonService personService;
    private Person subject;
    private Person known;

    @Before
    public void setup(){
        personRepository = Mockito.mock(PersonRepository.class);
        personService = new PersonService(personRepository);
        subject = new Person(1,"Juan");
        known = new Person(2, "Carlos");
        Mockito.when(personRepository.findById(1)).thenReturn(subject);
        Mockito.when(personRepository.findById(2)).thenReturn(known);
        Mockito.when(personRepository.save(subject)).thenReturn(subject);
    }

    @Test
    public void personServiceNotNull(){
        Assert.assertNotNull(personService);
    }

    @Test
    public void establishRelationNonNull(){
        Relation relation = new Relation(subject, known);
        Assert.assertNotNull(personService.establishRelation(relation));
    }

    @Test
    public void registerPersonNotNull(){
        Assert.assertThat(personService.registerPerson(subject), Matchers.is(subject));
    }

    @Test
    public void findByIdOne(){
        Assert.assertEquals(personService.findById(1), subject);
    }

    @Test
    public void a(){
        Relation relation = new Relation(subject, known);
        personService.establishRelation(relation);
        Assert.assertThat(personService.getPersonRelations(subject),
                Matchers.hasItem(known));
    }
}
