package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.UsuarioAlertaDTO;
import com.gs.alagamenos.model.UsuarioAlerta;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UsuarioAlertaMapperInterface {

	UsuarioAlertaDTO toDTO (UsuarioAlerta usuarioAlerta);
	
	UsuarioAlerta toEntity (UsuarioAlertaDTO toDTO);
	
}
