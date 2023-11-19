package br.com.project.SolarSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.project.SolarSystem")
public class SolarSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(SolarSystemApplication.class, args);
	}

}
