package com.globant.celebrity.finder;

import com.globant.celebrity.finder.endpoint.PersonEndpointTest;
import com.globant.celebrity.finder.model.Person;
import com.globant.celebrity.finder.model.RelationTest;
import com.globant.celebrity.finder.repository.PersonLocalRepositoryTest;
import com.globant.celebrity.finder.service.PersonServiceTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@RunWith(Suite.class)
@Suite.SuiteClasses({PersonLocalRepositoryTest.class, PersonServiceTest.class, RelationTest.class,
		PersonEndpointTest.class})
@SpringBootTest
public class CelebrityFinderApplicationTests {

	@Test
	public void contextLoads() {
	}

}
