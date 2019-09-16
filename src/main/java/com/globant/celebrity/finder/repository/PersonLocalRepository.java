package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.exception.PersonLocalRepositoryException;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.util.CsvDataHandler;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonLocalRepository implements PersonRepository{

    private Set<Person> people;
    private Map<Integer, Set<Integer>> relationsMap;
    private CsvDataHandler csvDataHandler = new CsvDataHandler();

    public PersonLocalRepository(){
        readPeopleFromCsvFile();
        readRelationsFromCsvFile();
        buildRelations();
    }

    private void readPeopleFromCsvFile(){
        this.people = new HashSet<>(csvDataHandler.getListFromCsv(Person.class, "LocalData.csv"));
    }

    private void readRelationsFromCsvFile(){
        this.relationsMap = csvDataHandler.getIntegerMapOfSetFromCsv("LocalRelations.csv");
    }

    private void buildRelations(){
        people.parallelStream()
                .filter(this::hasRelations)
                .forEach(this::establishRelation);
    }

    private boolean hasRelations(Person subject){
        return relationsMap.containsKey(subject.getId());
    }

    private void establishRelation(Person subject){
        relationsMap.get(subject.getId())
                .forEach(integer -> subject.getKnownPeople().add(findById(integer)));
    }

    @Override
    public Person save(Person person){
        if(people.add(person)) {
            person.setId(setNewId());
            return person;
        }
        throw new PersonLocalRepositoryException("It's not possible to save person");
    }

    public int setNewId(){
        return people.parallelStream().max((p1, p2) -> Integer.compare(p1.getId(), p2.getId())).get().getId() + 1;
    }

    @Override
    public Person findById(int id){
        return people.parallelStream()
                        .filter(p -> p.getId() == id)
                        .findFirst()
                        .orElse(null);
    }

    @Override
    public List<Person> findAll(){
        return new LinkedList<>(people);
    }

    @Override
    public Set<Person> getPersonRelations(Person person){
        return new HashSet<>(findById(person.getId()).getKnownPeople());
    }
}