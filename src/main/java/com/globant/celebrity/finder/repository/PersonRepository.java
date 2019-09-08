package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;

import java.util.HashSet;
import java.util.Set;

public class PersonRepository {

    private HashSet<Person> personHashSet;

    public PersonRepository(){
        personHashSet = new HashSet<>();
    }

    public boolean save(Person person){
        return personHashSet.add(person);
    }

    public Person findById(int id){
        return personHashSet.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .get();
    }

    public Set<Person> findAll(){
        return new HashSet<Person>(personHashSet);
    }

    public boolean contains(Person person){
        return personHashSet.contains(person);
    }

}
