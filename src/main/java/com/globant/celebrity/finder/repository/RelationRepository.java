package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;

import java.util.*;

public class RelationRepository {

    private Map<Person, Set<Person>> relationRepository;

    public RelationRepository(){
        relationRepository = new HashMap<>();
    }

    public void save(Relation relation){
        Person subject = relation.getSubject();
        Person known = relation.getKnown();
        Set<Person> knownPersons = getRelationsCollection(subject);
        knownPersons.add(known);
        relationRepository.put(subject, knownPersons);
    }

    private Set<Person> getRelationsCollection(Person person){
        Set<Person> knownPersons = relationRepository.get(person);
        if(Objects.isNull(knownPersons)){
            knownPersons = new HashSet<>();
        }
        return knownPersons;
    }

    public Set<Person> getRelations(Person person){
        return new HashSet<Person>(getRelationsCollection(person));
    }
}
