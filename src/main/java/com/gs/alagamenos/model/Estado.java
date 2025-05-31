package com.gs.alagamenos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "Esta classe irá representar a entidade Estado")
@Data
@Entity
@Table(name = "ESTADO")
public class Estado {

	@Schema(description = "Este atributo representa a chave primária ID", example = "1")
	@Id
	private long id;
	
	@Schema(description = "Este atributo representa o nome de um estado")
	@NotEmpty(message = "Não é permitido a inserção de estado sem nome de identificação")
	private String nome_estado;
}
