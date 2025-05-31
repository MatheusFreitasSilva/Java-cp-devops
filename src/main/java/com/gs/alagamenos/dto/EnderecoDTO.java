package com.gs.alagamenos.dto;

import com.gs.alagamenos.model.Rua;
import com.gs.alagamenos.model.Usuario;

public class EnderecoDTO {

	private long id;
	private long numero_endereco;
	private String complemento;
	private Rua rua;
	private Usuario usuario;
	
	public EnderecoDTO() {}

	public EnderecoDTO(long id, long numero_endereco, String complemento, Rua rua, Usuario usuario) {
		super();
		this.id = id;
		this.numero_endereco = numero_endereco;
		this.complemento = complemento;
		this.rua = rua;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumero_endereco() {
		return numero_endereco;
	}

	public void setNumero_endereco(long numero_endereco) {
		this.numero_endereco = numero_endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
