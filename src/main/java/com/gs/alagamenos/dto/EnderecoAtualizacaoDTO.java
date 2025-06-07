package com.gs.alagamenos.dto;

public class EnderecoAtualizacaoDTO {
	
	private Long numero_endereco;
	private String complemento;
	private Long rua_id;
	
	public EnderecoAtualizacaoDTO() {}

	public EnderecoAtualizacaoDTO(Long numero_endereco, String complemento, Long rua_id) {
		super();
		this.numero_endereco = numero_endereco;
		this.complemento = complemento;
		this.rua_id = rua_id;
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
}
