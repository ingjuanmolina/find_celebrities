package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

@Qualifier("PersonDBRepository")
public interface PersonDBRepository extends PersonRepository, JpaRepository<Person, Integer> {
}
