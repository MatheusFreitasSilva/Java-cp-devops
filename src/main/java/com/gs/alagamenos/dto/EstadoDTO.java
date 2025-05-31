package com.gs.alagamenos.dto;

public class EstadoDTO {
	
	private long id;
	private String nome_estado;
	
	public EstadoDTO() {}
	
	public EstadoDTO(long id, String nome_estado) {
		super();
		this.id = id;
		this.nome_estado = nome_estado;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome_estado() {
		return nome_estado;
	}
	public void setNome_estado(String nome_estado) {
		this.nome_estado = nome_estado;
	}
	
	

}
