package com.izambrana.pruebatec4;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Pruebatec4Application {

	public static void main(String[] args) {
		SpringApplication.run(Pruebatec4Application.class, args);
	}

	//Personalización de Swagger
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("API AGENCIA DE VUELOS Y HOTELES")
				.version("1.0.0")
				.description("API que maneja métodos CRUD de hoteles y vuelos así como de reservas"));
	}

}
