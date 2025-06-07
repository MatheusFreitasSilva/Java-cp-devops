package com.gs.alagamenos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Esta classe irá representar a entidade Bairro")
@Data
@Entity
@Table(name = "BAIRRO")
public class Bairro {

	@Schema(description = "Este atributo representa a chave primária ID", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "Não é permitido a inserção de bairro sem identificação")
	@Schema(description = "Este atributo representa o nome de um bairro")
	private String nome_bairro;
	
	@ManyToOne
	@JoinColumn(name= "CIDADE_ID", nullable = false)
	@NotNull(message = "Não é permitido incluir bairro sem cidade")
	@Schema(description = "Este atributo representa a instância de uma cidade")
	private Cidade cidade;
	
}
