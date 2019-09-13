package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonDBRepository extends JpaRepository<Person, Integer> {

    Person findById(int id);
}
