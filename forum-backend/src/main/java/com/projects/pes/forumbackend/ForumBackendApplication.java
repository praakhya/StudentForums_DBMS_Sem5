package com.projects.pes.forumbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

@SpringBootApplication(scanBasePackages = {"com.projects.pes.forumbackend"})
public class ForumBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumBackendApplication.class, args);
	}

}
