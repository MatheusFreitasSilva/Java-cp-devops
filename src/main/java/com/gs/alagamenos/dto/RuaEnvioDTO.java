package com.gs.alagamenos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public class RuaEnvioDTO {

    private String nome_rua;

    private String observacao;

    private Long bairro_id;
    
    public RuaEnvioDTO() {}

	public RuaEnvioDTO(String nome_rua, String observacao, Long bairro_id) {
		super();
		this.nome_rua = nome_rua;
		this.observacao = observacao;
		this.bairro_id = bairro_id;
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

	public Long getIdBairro() {
		return bairro_id;
	}

	public void setIdBairro(Long idBairro) {
		this.bairro_id = idBairro;
	}
}
