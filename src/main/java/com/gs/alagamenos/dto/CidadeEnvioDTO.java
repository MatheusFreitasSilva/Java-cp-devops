package com.gs.alagamenos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public class CidadeEnvioDTO {

	private String nome_cidade;
	private Long estado_id;
	
	public CidadeEnvioDTO() {}

	public CidadeEnvioDTO(String nome_cidade, Long estado_id) {
		super();
		this.nome_cidade = nome_cidade;
		this.estado_id = estado_id;
	}

	public String getNome_cidade() {
		return nome_cidade;
	}

	public void setNome_cidade(String nome_cidade) {
		this.nome_cidade = nome_cidade;
	}

	public Long getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(Long estado_id) {
		this.estado_id = estado_id;
	}
}
