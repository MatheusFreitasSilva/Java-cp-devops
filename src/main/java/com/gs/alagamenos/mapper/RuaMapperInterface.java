package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.RuaDTO;
import com.gs.alagamenos.model.Rua;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RuaMapperInterface {

	RuaDTO toDTO(Rua rua);
	
	Rua toEntity(RuaDTO toDTO);
	
}
