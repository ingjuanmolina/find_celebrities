package com.globant.celebrity.finder;

import com.globant.celebrity.finder.endpoint.PersonEndpointTest;
import com.globant.celebrity.finder.model.RelationTest;
import com.globant.celebrity.finder.repository.PersonLocalRepositoryTest;
import com.globant.celebrity.finder.service.PersonServiceTest;
import com.globant.celebrity.finder.util.CsvDataHandlerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({PersonLocalRepositoryTest.class, PersonServiceTest.class, RelationTest.class,
		PersonEndpointTest.class, CsvDataHandlerTest.class})
@SpringBootTest
public class CelebrityFinderApplicationTests {

}
