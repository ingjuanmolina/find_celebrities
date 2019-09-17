package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository {
    Person save(Person person);

    Person findById(int id);

    List<Person> findAll();
}
