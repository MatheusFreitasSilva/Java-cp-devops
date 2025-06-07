package com.gs.alagamenos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public class BairroEnvioDTO {

	private String nome_bairro;
	private Long cidade_id;
	
	public BairroEnvioDTO() {}

	public BairroEnvioDTO(String nome_bairro, Long cidade_id) {
		super();
		this.nome_bairro = nome_bairro;
		this.cidade_id = cidade_id;
	}

	public String getNome_bairro() {
		return nome_bairro;
	}

	public void setNome_bairro(String nome_bairro) {
		this.nome_bairro = nome_bairro;
	}

	public Long getCidade_id() {
		return cidade_id;
	}

	public void setCidade_id(Long cidade_id) {
		this.cidade_id = cidade_id;
	}
}
