package com.globant.celebrity.finder.model;

import com.globant.celebrity.finder.exception.PersonNotFoundException;
import com.globant.celebrity.finder.service.PersonService;

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
                        .filter(c -> c.getPersonSet().size() == 0)
                        .collect(Collectors.toList());

        for(Person candidate : personWithOutKnownPeople){
            boolean isCelebrity = guests.parallelStream()
                    .filter(g -> !g.equals(candidate))
                    .allMatch(g -> guestKnowsCandidate(g, candidate));
            if(isCelebrity){
                return candidate;
            }
        }
        throw new PersonNotFoundException("There are no celebrities");
    }

    private boolean knowsSomeone(Person person){
        return personService.getPersonRelations(person).size() > 0;
    }

    private boolean guestKnowsCandidate(Person guest, Person candidate){
        if(knowsSomeone(guest)){
            return personService.getPersonRelations(guest).contains(candidate);
        }
        return false;
    }
}
