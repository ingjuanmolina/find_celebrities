package com.globant.celebrity.finder.model;

import com.globant.celebrity.finder.exception.PersonNotFoundException;
import com.globant.celebrity.finder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CelebrityValidator {

    private PersonService personService;

    public CelebrityValidator(PersonService personService) {
        this.personService = personService;
    }

    public Person findCelebrity(){
        List<Person> guests = personService.getAll();
        List<Person> personWithOutKnownPeople = guests.parallelStream()
                        .filter(c -> c.getPersonSet().isEmpty())
                        .collect(Collectors.toList());
        for(Person candidate : personWithOutKnownPeople){
            boolean isCelebrity = guests.parallelStream()
                    .filter(g -> !g.equals(candidate))
                    .allMatch(g -> g.knowsPerson(candidate));
            if(isCelebrity){
                return candidate;
            }
        }
        throw new PersonNotFoundException("There are no celebrities");
    }
}
