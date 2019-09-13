package com.globant.celebrity.finder.model;

import com.globant.celebrity.finder.exception.IllegalRelationException;

public class Relation {

    private Person subject;
    private Person known;

    public Relation() {
    }

    public Relation(Person subject, Person known) {
        if(subject.equals(known)){
            throw new IllegalRelationException("Can't relate a person with itself");
        }
        this.subject = subject;
        this.known = known;
    }

    public Person getSubject() {
        return subject;
    }

    public void setSubject(Person subject) {
        this.subject = subject;
    }

    public Person getKnown() {
        return known;
    }

    public void setKnown(Person known) {
        this.known = known;
    }
}
