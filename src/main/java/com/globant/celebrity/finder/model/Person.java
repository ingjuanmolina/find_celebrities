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
    private Set<Person> personSet;

    public Person(){}

    public Person(String name, Set<Person> personSet) {
        this.name = name;
        this.personSet = personSet;
    }

    public Person(int id, String name){
        this.id = id;
        this.name = name;
        this.personSet = new HashSet<>();
    }

    public Person(int id) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPersonSet() {
        if(this.personSet==null){
            this.personSet = new HashSet<>();
        }
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        if(personSet==null){
            personSet = new HashSet<>();
        }
        this.personSet = personSet;
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
        return String.format("Person id: %d", id);
    }
}
