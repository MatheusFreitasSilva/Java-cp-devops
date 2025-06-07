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

@Schema(description = "Esta classe irá representar a entidade Endereco")
@Data
@Entity
@Table(name = "ENDERECO")
public class Endereco {

	@Schema(description = "Este atributo representa a chave primária ID", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Schema(description = "Este atributo representa o número de um endereço")
	@NotEmpty(message = "Não é permitido a inserção de endereco sem identificação")
	private long numero_endereco;
	
	@Schema(description = "Este atributo representa o complemento de endereço. Pode ser nulo.")
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name= "RUA_ID", nullable = false)
	@NotNull(message = "Não é permitido incluir endereço sem rua")
	@Schema(description = "Este atributo representa a instância de uma rua.")
	private Rua rua;
	
	@ManyToOne
	@JoinColumn(name= "BAIRRO_ID", nullable = false)
	@NotNull(message = "Não é permitido incluir endereço sem usuario")
	@Schema(description = "Este atributo representa a instância de um usuario.")
	private Usuario usuario;
	
}
