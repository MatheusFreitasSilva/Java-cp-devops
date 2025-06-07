package com.gs.alagamenos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public class EstadoEnvioDTO {

	private String nome_estado;
	
	public EstadoEnvioDTO() {}

	public EstadoEnvioDTO(String nome_estado) {
		super();
		this.nome_estado = nome_estado;
	}

	public String getNome_estado() {
		return nome_estado;
	}

	public void setNome_estado(String nome_estado) {
		this.nome_estado = nome_estado;
	}
}
