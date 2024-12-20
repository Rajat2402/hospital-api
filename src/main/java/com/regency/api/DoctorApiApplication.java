package com.regency.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
@SpringBootApplication
@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Rajat", email = "rajatkukreja62@gmail.com", url = "www.google.com"), version = "1.0.0", description = "This is hospotal api", title = "This is hospital rest api"))
public class DoctorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
