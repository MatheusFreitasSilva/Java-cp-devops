package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.UsuarioDTO;
import com.gs.alagamenos.model.Usuario;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapperInterface {

	@Mapping(source = "dataNascimento", target = "dataNascimento")
	UsuarioDTO toDTO (Usuario usuario);
	
	@Mapping(source = "dataNascimento", target = "dataNascimento")
	Usuario toEntity (UsuarioDTO toDTO);
	
}
