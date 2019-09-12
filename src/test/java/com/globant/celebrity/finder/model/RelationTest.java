package com.globant.celebrity.finder.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RelationTest {

    private Relation relation;
    private Person subject;
    private Person known;

    @Test(expected = RuntimeException.class)
    public void createRelationWithSameIdThrowsException(){
        subject = new Person(1);
        known = new Person(1);
        relation = new Relation(subject, known);
    }
}
