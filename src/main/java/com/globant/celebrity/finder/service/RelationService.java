package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;
import com.globant.celebrity.finder.repository.RelationRepository;

import java.util.Set;

public class RelationService {

    private RelationRepository relationRepository;

    public RelationService(){
        relationRepository = new RelationRepository();
    }

    public void createRelation(Person subject, Person known){
        relationRepository.save(new Relation(subject, known));
    }

    public Set<Person> getPersonRelations(Person person){
        return relationRepository.getRelations(person);
    }
}
