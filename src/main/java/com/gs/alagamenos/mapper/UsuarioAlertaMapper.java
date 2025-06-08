package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.UsuarioAlertaDTO;
import com.gs.alagamenos.model.UsuarioAlerta;

@Component
public class UsuarioAlertaMapper {
	
private final UsuarioMapper usuarioMapper;
	
	public UsuarioAlertaMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

	public UsuarioAlertaDTO toDTO(UsuarioAlerta usuarioAlerta) {
		
		UsuarioAlertaDTO dto = new UsuarioAlertaDTO();
		
		dto.setAlerta(usuarioAlerta.getAlerta());
		dto.setId(usuarioAlerta.getId());
		dto.setUsuario(usuarioMapper.toDTO(usuarioAlerta.getUsuario()));
		
		return dto;
	}
	
}
