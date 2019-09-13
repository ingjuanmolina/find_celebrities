package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.exception.PersonLocalRepositoryException;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.util.CsvDataHandler;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonLocalRepository implements PersonRepository{

    private Set<Person> people;
    private int id;

    public PersonLocalRepository(){
        this.id = 0;
        this.people = new HashSet<>();
    }

    public PersonLocalRepository(CsvDataHandler csvDataHandler) {
        this.people = new HashSet<>(csvDataHandler.loadObjectList(Person.class, "LocalData.csv"));
    }

    @Override
    public Person save(Person person){
        if(people.add(person)) {
            person.setId(++this.id);
            return person;
        }
        throw new PersonLocalRepositoryException("It's not possible to save person");
    }

    @Override
    public Person findById(int id){
        return people.parallelStream()
                        .filter(p -> p.getId() == id)
                        .findFirst()
                        .orElse(null);
    }

    @Override
    public List<Person> getAll(){
        return new LinkedList<>(people);
    }

    @Override
    public Set<Person> getPersonRelations(Person person){
        return new HashSet<>(findById(person.getId()).getKnownPeople());
    }
}
