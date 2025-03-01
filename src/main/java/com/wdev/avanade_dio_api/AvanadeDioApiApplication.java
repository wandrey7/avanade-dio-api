package com.wdev.avanade_dio_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "Java RestFull API",
		version = "1.0",
		description = "API criada no curso da Digital Innovation One em parceria com a Avanade"
))
@SpringBootApplication
public class AvanadeDioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvanadeDioApiApplication.class, args);
	}

}
