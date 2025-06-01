package com.gs.alagamenos.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {
	@Bean
	OpenAPI configurarSwagger() {
		return new OpenAPI().info(new Info()
											.title("Projeto de Gestão de Alertas de alagamento - Alagamenos")
											.description("Este projeto visa gerenciar as relações e "
											+ "as principais funcionalidades referentes aos relacionamentos"
											+ " entre os elementos "
											+ "estado, cidade, bairro, rua, endereco, alerta e usuario "
											+ "em uma aplicação de alto nível de "
											+ "maturidade utilizando de recursos do framework SpringBoot")
											.summary("Gerenciador de alertas de alagamentos")
											.version("v1.0.0")
											.license(new License().url("https://github.com/GS-2025-1/Java.git")
																  .name("Alagamenos")));
	}

}
