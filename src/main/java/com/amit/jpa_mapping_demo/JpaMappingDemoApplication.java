package com.amit.jpa_mapping_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employee Address management system",
     description = "Project to mantain employee and their address using JPA to demonstrate JPA table mapping.",
     version = "v1",
     contact = @Contact(name = "Amit Soni", email = "asoni170@gmail.com", url = "https://www.google.com"),
     license = @License(name = "Appache 2.0", url = "https://www.google.com")))
@EntityScan("com.amit.entity")
@EnableJpaRepositories("com.amit.repository")
@ComponentScan({"com.amit.service.impl", "com.amit.exception", "com.amit.controller",
                 "com.amit.config", "com.amit.util"})
public class JpaMappingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaMappingDemoApplication.class, args);
	}

}
