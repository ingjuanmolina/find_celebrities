package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDBRepository extends JpaRepository<Person, Integer> {
    Person findById(int id);
}
