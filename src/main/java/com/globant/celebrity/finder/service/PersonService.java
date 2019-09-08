package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.repository.PersonRepository;

import java.util.Set;

public class PersonService {

    private PersonRepository personRepository;

    public PersonService(){
        personRepository = new PersonRepository();
    }

    public boolean registerPerson(Person person){
        return personRepository.save(person);
    }

    public Person finbById(int id){
        return personRepository.findById(id);
    }

    public boolean isRegistered(Person person){
        return personRepository.contains(person);
    }

    public Set<Person> getAll(){
        return personRepository.findAll();
    }
}
