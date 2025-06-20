package com.example.crud_spring_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrudSpringAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrudSpringAppApplication.class, args);
	}
}