package com.gs.alagamenos.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Esta classe irá representar a entidade Usuario")
@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Schema(description = "Este atributo representa a chave primária ID", example = "1")
	@Id
	private long id;
	
	@Schema(description = "Este atributo representa o nome de um usuário")
	@NotEmpty(message = "Não é permitido a inserção de usuario sem identificação")
	private String nome;
	
	@Schema(description = "Este atributo representa a data de nascimento de um usuário")
	@NotNull(message = "Não é permitido a inserção de usuario sem data de nascimento")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@Schema(description = "Este atributo representa o número de telefone de um usuário")
	@Min(value = 1000000000000L, message = "O telefone deve ter exatamente 13 dígitos")
	@Max(value = 9999999999999L, message = "O telefone deve ter exatamente 13 dígitos")
	private long telefone;
	
	@Schema(description = "Este atributo representa o email de um usuário")
	@NotEmpty(message = "Não é permitido a inserção de usuario sem email")
	private String email;
	
	@Schema(description = "Este atributo representa a senha de um usuário")
	@NotEmpty(message = "Não é permitido a inserção de usuario sem senha")
	private String senha;	
}
