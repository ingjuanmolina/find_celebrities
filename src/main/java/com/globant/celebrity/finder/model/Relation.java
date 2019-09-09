package com.globant.celebrity.finder.model;

public class Relation {

    private int id;
    private Person subject;
    private Person known;

    public Relation(Person subject, Person known) {
        if(subject.equals(known)){
            throw new RuntimeException("Can´t relate a person with itself");
        }
        this.subject = subject;
        this.known = known;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
