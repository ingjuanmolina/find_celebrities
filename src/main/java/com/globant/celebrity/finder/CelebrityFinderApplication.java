package com.globant.celebrity.finder;

import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.util.CsvDataHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CelebrityFinderApplication {



	public static void main(String[] args) { SpringApplication.run(CelebrityFinderApplication.class, args);
		CsvDataHandler dataLoader = new CsvDataHandler();
		List<Person> people = dataLoader.loadObjectList(Person.class, "LocalData.csv");}

}
