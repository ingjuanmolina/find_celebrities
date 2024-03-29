package com.globant.celebrity.finder.repository;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.PersonBuilder;
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
        charles = repository.findById(1);
        valtteri = repository.findById(2);
        lewis = repository.findById(3);
        daniel = repository.findById(4);
    }

    @Test
    public void saveNewPersonReturnsPerson() {
        Person niko = new PersonBuilder().withName("Niko").build();
        Person result = repository.save(niko);
        Assert.assertTrue(result instanceof Person);
    }

    @Test
    public void saveNewPersonIncrementsId() {
        Person niko = new PersonBuilder().withName("Niko").build();
        Person alexander = new PersonBuilder().withName("Alexander").build();
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
    public void findByIdFortyIsNull() {
        Assert.assertNull(repository.findById(40));
    }

    @Test
    public void getAll() {
        Assert.assertThat(repository.findAll(), Matchers.hasItems(charles, valtteri, lewis, daniel));
    }

    @Test
    public void getPersonRelations() {
        charles.getKnownPeople().add(valtteri);
        charles.getKnownPeople().add(lewis);
        Assert.assertThat(repository.getPersonRelations(charles), Matchers.hasItems(valtteri, lewis));
    }

    @Test
    public void findByIdFromCsvLoadedData(){
        PersonLocalRepository repository = new PersonLocalRepository();
        Assert.assertThat(repository.findById(1), Matchers.is(charles));
    }

    @Test
    public void charlesHasRelationsFromCsvLoadedData(){
        PersonLocalRepository repository = new PersonLocalRepository();
        Person nico = repository.findById(5);
        Person sergio = repository.findById(7);
        Assert.assertThat(charles.getKnownPeople(), Matchers.hasItems(lewis, nico, sergio));
    }
}