package com.gs.alagamenos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public class EnderecoEnvioDTO {

	private Long numero_endereco;
	private String complemento;
	private Long rua_id;
	private Long usuario_id;
	
	public EnderecoEnvioDTO() {}

	public EnderecoEnvioDTO(Long numero_endereco, String complemento, Long rua_id, Long usuario_id) {
		super();
		this.numero_endereco = numero_endereco;
		this.complemento = complemento;
		this.rua_id = rua_id;
		this.usuario_id = usuario_id;
	}

	public Long getNumero_endereco() {
		return numero_endereco;
	}

	public void setNumero_endereco(Long numero_endereco) {
		this.numero_endereco = numero_endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getRua_id() {
		return rua_id;
	}

	public void setRua_id(Long rua_id) {
		this.rua_id = rua_id;
	}

	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}
}
