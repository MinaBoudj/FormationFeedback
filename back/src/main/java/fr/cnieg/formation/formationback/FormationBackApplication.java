package fr.cnieg.formation.formationback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
public class FormationBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormationBackApplication.class, args);
	}

}
