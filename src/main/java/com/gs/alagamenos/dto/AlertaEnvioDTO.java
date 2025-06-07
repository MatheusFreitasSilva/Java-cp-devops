package com.gs.alagamenos.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(hidden = true)
public class AlertaEnvioDTO {
	
	@NotBlank(message = "Nome da rua é obrigatório")
	private String mensagem;
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "data_criacao")
	private Date dataCriacao;
	@NotNull(message = "ID do bairro é obrigatório")
	private Long rua_id;
	
	public AlertaEnvioDTO() {}
	
	public AlertaEnvioDTO(String mensagem, Date dataCriacao, long rua_id) {
		super();
		this.mensagem = mensagem;
		this.dataCriacao = dataCriacao;
		this.rua_id = rua_id;
	}



	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getRuaId() {
		return rua_id;
	}

	public void setRuaId(Long rua_id) {
		this.rua_id = rua_id;
	}

}
