package com.gs.alagamenos.dto;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.alagamenos.model.Rua;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public class AlertaDTO {
	
	private long id;
	private String mensagem;
	private LocalDate dataCriacao;
	private Rua rua;

	public AlertaDTO() {}

	public AlertaDTO(long id, String mensagem, LocalDate data_criacao, Rua rua) {
		super();
		this.id = id;
		this.mensagem = mensagem;
		this.dataCriacao = data_criacao;
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
		return dataCriacao;
	}

	public void setData_criacao(LocalDate data_criacao) {
		this.dataCriacao = data_criacao;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}
}
