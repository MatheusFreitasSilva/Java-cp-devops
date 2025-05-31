package com.gs.alagamenos.dto;

import com.gs.alagamenos.model.Cidade;

public class BairroDTO {
	
	private long id;
	private String nome_bairro;
	private Cidade cidade;
	
	public BairroDTO() {}
	
	public BairroDTO(long id, String nome_bairro, Cidade cidade) {
		super();
		this.id = id;
		this.nome_bairro = nome_bairro;
		this.cidade = cidade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome_bairro() {
		return nome_bairro;
	}

	public void setNome_bairro(String nome_bairro) {
		this.nome_bairro = nome_bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}
