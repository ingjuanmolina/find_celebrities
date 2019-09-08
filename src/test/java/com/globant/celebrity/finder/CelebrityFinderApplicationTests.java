package com.globant.celebrity.finder;

import com.globant.celebrity.finder.repository.PersonRepositoryTest;
import com.globant.celebrity.finder.repository.RelationRepositoryTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(Suite.class)
@Suite.SuiteClasses({PersonRepositoryTest.class, RelationRepositoryTest.class})
@SpringBootTest
public class CelebrityFinderApplicationTests {

	@Test
	public void contextLoads() {
	}

}
