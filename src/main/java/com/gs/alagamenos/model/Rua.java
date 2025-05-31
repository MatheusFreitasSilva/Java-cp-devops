package com.gs.alagamenos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Esta classe irá representar a entidade Rua")
@Data
@Entity
@Table(name = "RUA")
public class Rua {

	@Schema(description = "Este atributo representa a chave primária ID", example = "1")
	@Id
	private long id;
	
	@NotEmpty(message = "Não é permitido a inserção de rua sem identificação")
	@Schema(description = "Este atributo representa a o nome de uma rua")
	private String nome_rua;
	
	@Schema(description = "Este atributo representa uma observação em uma rua. Pode ser nulo")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name= "BAIRRO_ID", nullable = false)
	@NotNull(message = "Não é permitido incluir rua sem bairro")
	@Schema(description = "Este atributo representa a instância de um bairro")
	private Bairro bairro;

	
}
