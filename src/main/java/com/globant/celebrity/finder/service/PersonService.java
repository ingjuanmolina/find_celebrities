package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.exception.PersonNotFoundException;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;
import com.globant.celebrity.finder.repository.PersonDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PersonService {

    private PersonDBRepository personDBRepository;

    @Autowired
    public PersonService(PersonDBRepository personDBRepository){
        this.personDBRepository = personDBRepository;
    }

    public Person registerPerson(Person person){
        return personDBRepository.save(person);
    }

    public Relation establishRelation(Relation relation){
        Person subject = findById(relation.getSubject().getId());
        Person known = findById(relation.getKnown().getId());
        subject.getKnownPeople().add(known);
        personDBRepository.save(subject);
        return new Relation(subject, known);
    }

    public Person findById(int id) {
        Person person = personDBRepository.findById(id);
        if(Objects.isNull(person)){
            throw new PersonNotFoundException(String.format("Person with id %d was not found", id));
        }
        return person;
    }

    public List<Person> getAll(){
        return personDBRepository.findAll();
    }

    public Set<Person> getPersonRelations(Person person){
        return personDBRepository.findById(person.getId()).getKnownPeople();
    }
}
