package com.gs.alagamenos.dto;

import com.gs.alagamenos.model.Estado;

public class CidadeDTO {
	
	private long id;
	private String nome_cidade;
	private Estado estado;
	
	public CidadeDTO() {}
	
	public CidadeDTO(long id, String nome_cidade, Estado estado) {
		super();
		this.id = id;
		this.nome_cidade = nome_cidade;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome_cidade() {
		return nome_cidade;
	}

	public void setNome_cidade(String nome_cidade) {
		this.nome_cidade = nome_cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
