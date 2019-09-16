package com.globant.celebrity.finder.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PersonBuilder {

    private int id;
    private String name;
    private Set<Person> knownPeople;

    public PersonBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public PersonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder withKnownPeople(Set<Person> knownPeople) {
        this.knownPeople = knownPeople;
        return this;
    }

    public Person build(){
        Person person = new Person();
        person.setId(this.id);
        person.setName(this.name);
        if(Objects.isNull(this.knownPeople)){
            this.knownPeople = new HashSet<>();
        }
        person.setKnownPeople(this.knownPeople);
        return person;
    }
}
