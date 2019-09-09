package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.exception.PersonNotFoundException;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.repository.PersonH2Repository;
import com.globant.celebrity.finder.repository.PersonRepository;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PersonService {

    private PersonH2Repository personRepository;

    @Autowired
    public PersonService(PersonH2Repository personRepository){
        this.personRepository = personRepository;
    }

    public Person registerPerson(Person person){
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
        Person found = personRepository.getOne(person.getId());
        if(Objects.nonNull(found)){
            return true;
        }
        return false;
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public Set<Person> getPersonRelations(Person person){
        return person.getPersonSet();
    }
}
