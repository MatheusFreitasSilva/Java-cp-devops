package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.UsuarioDTO;
import com.gs.alagamenos.model.Usuario;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapperInterface {

	UsuarioDTO toDTO (Usuario usuario);
	
	Usuario toEntity (UsuarioDTO toDTO);
	
}
