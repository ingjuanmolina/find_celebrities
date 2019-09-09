package com.globant.celebrity.finder.service;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.Relation;
import com.globant.celebrity.finder.repository.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RelationService {

    private RelationRepository relationRepository;

    @Autowired
    public RelationService(RelationRepository relationRepository){
        this.relationRepository = relationRepository;
    }

    public Relation createRelation(Person subject, Person known){
        Relation relation = new Relation(subject, known);
        relationRepository.save(relation);
        return relation;
    }

    public Relation createRelation(Relation relation){
        relationRepository.save(relation);
        return relation;
    }

    public Set<Person> getPersonRelations(Person person){
        return relationRepository.getRelations(person);
    }
}
