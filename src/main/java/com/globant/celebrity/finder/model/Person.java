package com.globant.celebrity.finder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JoinTable(name = "relation", joinColumns = {
            @JoinColumn(name = "subject", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "known", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Person> knownPeople;

    public Person(){}

    boolean knowsPerson(Person person){
        return this.getKnownPeople().contains(person);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Set<Person> getKnownPeople() {
        if(this.knownPeople ==null){
            this.knownPeople = new HashSet<>();
        }
        return knownPeople;
    }

    public void setKnownPeople(Set<Person> knownPeople) {
        if(knownPeople ==null){
            knownPeople = new HashSet<>();
        }
        this.knownPeople = knownPeople;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Person)){
            return false;
        }
        return this.id == ((Person) obj).getId();
    }

    @Override
    public String toString() {
        return String.format("Person id: %d, %s", id, name);
    }
}