package com.mattaeng.mattaengapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MattaengApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MattaengApiApplication.class, args);
	}

}
