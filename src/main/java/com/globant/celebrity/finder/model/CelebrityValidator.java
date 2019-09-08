package com.globant.celebrity.finder.model;

import com.globant.celebrity.finder.service.PersonService;
import com.globant.celebrity.finder.service.RelationService;

public class CelebrityValidator {

    private PersonService personService = new PersonService();
    private RelationService relationService = new RelationService();

    public CelebrityValidator(PersonService personService, RelationService relationService) {
        this.personService = personService;
        this.relationService = relationService;
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
        return relationService.getPersonRelations(person).size() > 0;
    }

    private boolean guestKnowsCandidate(Person guest, Person candidate){
        if(knowsSomeone(guest)){
            return relationService.getPersonRelations(guest).contains(candidate);
        }
        return false;
    }
}
