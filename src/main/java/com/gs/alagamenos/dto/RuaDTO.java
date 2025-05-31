package com.gs.alagamenos.dto;

import com.gs.alagamenos.model.Bairro;

public class RuaDTO {
	
	private long id;
	private String nome_rua;
	private String observacao;
	private Bairro bairro;

	public RuaDTO() {}

	public RuaDTO(long id, String nome_rua, String observacao, Bairro bairro) {
		super();
		this.id = id;
		this.nome_rua = nome_rua;
		this.observacao = observacao;
		this.bairro = bairro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome_rua() {
		return nome_rua;
	}

	public void setNome_rua(String nome_rua) {
		this.nome_rua = nome_rua;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
}
