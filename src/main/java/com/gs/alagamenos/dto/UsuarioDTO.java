package com.gs.alagamenos.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public class UsuarioDTO {

	private long id;
	private String nome;
	private String email;
	private String senha;
	private long telefone;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	
	public UsuarioDTO() {}

	public UsuarioDTO(long id, String nome, String email, String senha, long telefone, Date data_nascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.dataNascimento = data_nascimento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date data_nascimento) {
		this.dataNascimento = data_nascimento;
	}
}
