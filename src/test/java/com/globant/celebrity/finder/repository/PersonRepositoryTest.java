package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PersonRepositoryTest {

    private Person personA;
    private Person personB;
    private Person personC;
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        personA = new Person(1);
        personB = new Person(2);
        personC = new Person(3);
        personRepository = new PersonRepository();
    }

    @Test
    public void save() {
        Assert.assertTrue(personRepository.save(personA));
    }

    @Test
    public void findById() {
        personRepository.save(personA);
        Assert.assertEquals(personRepository.findById(1), new Person(1));
    }

    @Test
    public void findAll() {
        personRepository.save(personA);
        personRepository.save(personB);
        Matchers.hasItems(personA, personB);
    }

    @Test
    public void personCIsNotPresent(){
        personRepository.save(personA);
        personRepository.save(personB);
        Assert.assertFalse(personRepository.contains(personC));
    }
}