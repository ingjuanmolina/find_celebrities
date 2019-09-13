package com.globant.celebrity.finder.util;

import com.globant.celebrity.finder.model.Person;
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
        Assert.assertThat(people, Matchers.hasItems(new Person(1, "Charles"), new Person(3, "Lewis")));
    }

    @Test
    public void writeDataAndThenRetrieveValue(){
        Person person = new Person(21, "Juan");
        csvDataHandler = new CsvDataHandler();
        csvDataHandler.write(person, "LocalData.csv");
        Assert.assertThat(csvDataHandler.loadObjectList(Person.class, "LocalData.csv"), Matchers.hasItems(person));
    }

}
