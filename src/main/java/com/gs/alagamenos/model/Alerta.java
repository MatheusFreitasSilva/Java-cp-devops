package com.gs.alagamenos.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Alerta {

	@Schema(description = "Este atributo representa a chave primária ID", example = "1")
	@Id
	private long id;
	
	@Schema(description = "Este atributo representa a mensagem de um alerta")
	@NotEmpty(message = "Não é permitido a inserção de alerta sem uma mensagem")
	private String mensagem;
	
	@Schema(description = "Este atributo representa a data de criação de um alerta")
	@NotNull(message = "Não é permitido a inserção de alerta sem data de criação")
	private LocalDate dataCriacao;
	
	@ManyToOne
	@JoinColumn(name= "RUA_ID", nullable = false)
	@NotNull(message = "Não é permitido incluir alerta sem rua")
	@Schema(description = "Este atributo representa a instância de uma rua.")
	private Rua rua;
	
}
