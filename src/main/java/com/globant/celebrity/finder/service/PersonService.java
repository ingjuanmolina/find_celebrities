package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.exception.PersonNotFoundException;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;
import com.globant.celebrity.finder.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person registerPerson(Person person){
        return personRepository.save(person);
    }

    public Relation establishRelation(Relation relation){
        Person subject = findById(relation.getSubject().getId());
        Person known = findById(relation.getKnown().getId());
        subject.getPersonSet().add(known);
        personRepository.save(subject);
        return new Relation(subject, known);
    }

    public Person findById(int id) {
        Person person = personRepository.findById(id);
        if(Objects.isNull(person)){
            throw new PersonNotFoundException(String.format("Person with id %d was not found", id));
        }
        return person;
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public Set<Person> getPersonRelations(Person person){
        return personRepository.findById(person.getId()).getPersonSet();
    }
}
