package com.globant.celebrity.finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.globant.celebrity.finder.repository")
public class CelebrityFinderApplication {

	public static void main(String[] args) { SpringApplication.run(CelebrityFinderApplication.class, args); }

}
