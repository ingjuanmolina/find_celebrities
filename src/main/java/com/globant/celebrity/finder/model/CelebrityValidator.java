package com.globant.celebrity.finder.model;

import com.globant.celebrity.finder.service.PersonService;
import com.globant.celebrity.finder.service.RelationServiceLocal;

public class CelebrityValidator {

    private PersonService personService;
    private RelationServiceLocal relationServiceLocal;

    public CelebrityValidator(PersonService personService, RelationServiceLocal relationServiceLocal) {
        this.personService = personService;
        this.relationServiceLocal = relationServiceLocal;
    }

    public Person findCelebrity(){
        for(Person candidate : personService.getAll()){
            boolean isCelebrity = true;
            if(!knowsSomeone(candidate)){
                for(Person guest : personService.getAll()){
                    if(guest.equals(candidate)){
                        continue;
                    }
                    isCelebrity &= guestKnowsCandidate(guest, candidate);
                }
                if(isCelebrity){
                    return candidate;
                }
            }
        }
        return null;
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
