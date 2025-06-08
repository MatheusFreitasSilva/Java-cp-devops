package com.gs.alagamenos.dto;

import com.gs.alagamenos.model.Alerta;
import com.gs.alagamenos.model.Usuario;
import com.gs.alagamenos.model.UsuarioAlertaId;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(hidden = true)
public class UsuarioAlertaDTO {

	private UsuarioAlertaId id;
	private UsuarioDTO usuario;
	private Alerta alerta;
	
	public UsuarioAlertaDTO() {}

	public UsuarioAlertaDTO(UsuarioAlertaId id, UsuarioDTO usuario, Alerta alerta) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.alerta = alerta;
	}

	public UsuarioAlertaId getId() {
		return id;
	}

	public void setId(UsuarioAlertaId id) {
		this.id = id;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Alerta getAlerta() {
		return alerta;
	}

	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}
}
