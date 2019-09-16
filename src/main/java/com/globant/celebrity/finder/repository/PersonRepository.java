package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;

import java.util.List;
import java.util.Set;

public interface PersonRepository {

    Person save(Person person);

    Person findById(int id);

    List<Person> findAll();

    Set<Person> getPersonRelations(Person person);
}
