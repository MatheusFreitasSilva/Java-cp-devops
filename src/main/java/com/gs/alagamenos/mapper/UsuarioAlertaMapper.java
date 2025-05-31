package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.UsuarioAlertaDTO;
import com.gs.alagamenos.model.UsuarioAlerta;

@Component
public class UsuarioAlertaMapper {

	public UsuarioAlertaDTO toDTO(UsuarioAlerta usuarioAlerta) {
		
		UsuarioAlertaDTO dto = new UsuarioAlertaDTO();
		
		dto.setAlerta(usuarioAlerta.getAlerta());
		dto.setId(usuarioAlerta.getId());
		dto.setUsuario(usuarioAlerta.getUsuario());
		
		return dto;
	}
	
}
