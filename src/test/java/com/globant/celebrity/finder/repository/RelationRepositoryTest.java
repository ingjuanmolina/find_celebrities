package com.globant.celebrity.finder.repository;


import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class RelationRepositoryTest {

    private RelationRepository repository;
    private Person personA;
    private Person personB;
    private Person personC;

    @Before
    public void setup(){
        repository = new RelationRepository();
        personA = new Person(1);
        personB = new Person(2);
        personC = new Person(3);
        repository.save(new Relation(personA, personB));
        repository.save(new Relation(personA, personC));
        repository.save(new Relation(personB, personC));
    }

    @Test
    public void personAKnowsPersonB(){
        assertThat(repository.getRelations(personA), Matchers.hasItem(personB));
    }

    @Test
    public void personAKnowsPersonC(){
        assertThat(repository.getRelations(personA), Matchers.hasItem(personC));
    }

    @Test
    public void personBKnowsOnePerson(){
        assertThat(repository.getRelations(personB), Matchers.hasSize(1));
    }

    @Test
    public void personCKnowksNoOne(){
        assertThat(repository.getRelations(personC), Matchers.hasSize(0));
    }

}
