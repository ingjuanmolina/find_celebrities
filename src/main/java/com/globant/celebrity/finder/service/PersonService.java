package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.exception.PersonNotFoundException;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;
import com.globant.celebrity.finder.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonService(@Qualifier("PersonDBRepository") PersonRepository repository){
        this.repository = repository;
    }

    public Person registerPerson(Person person){
        return repository.save(person);
    }

    public Relation establishRelation(Relation relation){
        Person subject = findById(relation.getSubject().getId());
        Person known = findById(relation.getKnown().getId());
        subject.getKnownPeople().add(known);
        repository.save(subject);
        return new Relation(subject, known);
    }

    public Person findById(int id) {
        Person person = repository.findById(id);
        if(Objects.isNull(person)){
            throw new PersonNotFoundException(String.format("Person with id %d was not found", id));
        }
        return person;
    }

    public List<Person> getAll(){
        return repository.findAll();
    }

    public Set<Person> getPersonRelations(Person person){
        return repository.findById(person.getId()).getKnownPeople();
    }
}
