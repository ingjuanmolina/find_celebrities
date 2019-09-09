package com.globant.celebrity.finder.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinTable(name = "relation", joinColumns = {
            @JoinColumn(name = "subject", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "known", referencedColumnName = "id")})
    @ManyToMany
    private Set<Person> personSet;

    public Person(){}

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
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
