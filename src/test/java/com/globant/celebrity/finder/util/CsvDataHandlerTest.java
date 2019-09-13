package com.globant.celebrity.finder.util;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.PersonBuilder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class CsvDataHandlerTest {

    private static CsvDataHandler csvDataHandler;
    private static List<Person> people;

    @BeforeClass
    public static void init(){
        CsvDataHandler dataLoader = new CsvDataHandler();
        people = dataLoader.loadObjectList(Person.class, "LocalData.csv");
    }

    @Test
    public void dataWasLoaded(){
        Assert.assertFalse(people.isEmpty());
    }

    @Test
    public void collectionHasTwentyItems(){
        Assert.assertThat(people, Matchers.hasSize(20));
    }

    @Test
    public void collectionHasValidItems(){
        Person charles = new PersonBuilder().withId(1).withName("Charles").build();
        Person lewis = new PersonBuilder().withId(3).withName("Lewis").build();
        Assert.assertThat(people, Matchers.hasItems(charles, lewis));
    }

    @Test
    public void writeDataAndThenRetrieveValue(){
        Person person = new PersonBuilder().withId(21).withName("Juan").build();
        csvDataHandler = new CsvDataHandler();
        csvDataHandler.write(person, "LocalData.csv");
        Assert.assertThat(csvDataHandler.loadObjectList(Person.class, "LocalData.csv"), Matchers.hasItems(person));
    }

}
