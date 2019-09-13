package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.PersonBuilder;
import com.globant.celebrity.finder.model.Relation;
import com.globant.celebrity.finder.repository.PersonDBRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PersonServiceTest {


    private PersonDBRepository personDBRepository;
    private PersonService personService;
    private Person subject;
    private Person known;

    @Before
    public void setup(){
        personDBRepository = Mockito.mock(PersonDBRepository.class);
        personService = new PersonService(personDBRepository);
        subject = new PersonBuilder().withId(1).withName("Juan").build();
        known = new PersonBuilder().withId(2).withName("Carlos").build();
        Mockito.when(personDBRepository.findById(1)).thenReturn(subject);
        Mockito.when(personDBRepository.findById(2)).thenReturn(known);
        Mockito.when(personDBRepository.save(subject)).thenReturn(subject);
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
    public void subjectAndKnownAreRelated(){
        Relation relation = new Relation(subject, known);
        personService.establishRelation(relation);
        Assert.assertThat(personService.getPersonRelations(subject),
                Matchers.hasItem(known));
    }
}
