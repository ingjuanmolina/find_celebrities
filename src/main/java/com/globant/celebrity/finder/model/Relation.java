package com.globant.celebrity.finder.model;

import org.omg.SendingContext.RunTime;

public class Relation {

    private Person subject;
    private Person known;

    public Relation(Person subject, Person known) {
        if(subject.equals(known)){
            throw new RuntimeException("Can´t relate a person with itself");
        }
        this.subject = subject;
        this.known = known;
    }

    public Person getSubject() {
        return subject;
    }

    public Person getKnown() {
        return known;
    }
}
