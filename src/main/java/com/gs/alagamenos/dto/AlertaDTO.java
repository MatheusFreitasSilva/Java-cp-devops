package com.gs.alagamenos.dto;

import java.time.LocalDate;

import com.gs.alagamenos.model.Rua;

public class AlertaDTO {
	
	private long id;
	private String mensagem;
	private LocalDate data_criacao;
	private Rua rua;

	public AlertaDTO() {}

	public AlertaDTO(long id, String mensagem, LocalDate data_criacao, Rua rua) {
		super();
		this.id = id;
		this.mensagem = mensagem;
		this.data_criacao = data_criacao;
		this.rua = rua;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDate getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(LocalDate data_criacao) {
		this.data_criacao = data_criacao;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}
}
