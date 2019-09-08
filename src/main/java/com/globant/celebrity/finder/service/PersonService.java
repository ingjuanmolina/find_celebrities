package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.exception.PersonNotFoundException;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.repository.PersonRepository;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public boolean registerPerson(Person person){
        return personRepository.save(person);
    }

    public Person findById(int id) {
        Person person = personRepository.findById(id);
        if(Objects.isNull(person)){
            throw new PersonNotFoundException("Person not found");
        }
        return person;
    }

    public boolean isRegistered(Person person){
        return personRepository.contains(person);
    }

    public Set<Person> getAll(){
        return personRepository.findAll();
    }
}
