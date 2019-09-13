package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PersonLocalRepositoryTest {

    private PersonLocalRepository repository;
    private Person charles;
    private Person valtteri;
    private Person lewis;
    private Person daniel;

    @BeforeClass
    public static void init(){

    }

    @Before
    public void setUp() {
        repository = new PersonLocalRepository();
        charles = new Person("charles");
        valtteri = new Person("charles");
        lewis = new Person("charles");
        daniel = new Person("charles");
        repository.save(charles);
        repository.save(valtteri);
        repository.save(lewis);
        repository.save(daniel);
    }

    @Test
    public void saveNewPersonReturnsPerson() {
        Person niko = new Person("Niko");
        Person result = repository.save(niko);
        Assert.assertTrue(result instanceof Person);
    }

    @Test
    public void saveNewPersonIncrementsId() {
        Person niko = new Person("Niko");
        Person alexander = new Person("Alexander");
        Person firstResult = repository.save(niko);
        Person secondResult = repository.save(alexander);
        Assert.assertTrue(secondResult.getId() == (firstResult.getId() + 1));
    }

    @Test
    public void findByIdOneReturnCharles() {
        Person result = repository.findById(1);
        Assert.assertTrue(result.equals(charles));
    }

    @Test
    public void findByIdTwentyIsNull() {
        Assert.assertNull(repository.findById(20));
    }

    @Test
    public void getAll() {
        Assert.assertThat(repository.getAll(), Matchers.hasItems(charles, valtteri, lewis, daniel));
    }

    @Test
    public void getPersonRelations() {
        charles.getPersonSet().add(valtteri);
        charles.getPersonSet().add(lewis);
        Assert.assertThat(repository.getPersonRelations(charles), Matchers.hasItems(valtteri, lewis));
    }
}