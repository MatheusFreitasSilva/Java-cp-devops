package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.AlertaDTO;
import com.gs.alagamenos.model.Alerta;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AlertaMapperInterface {
	
	@Mapping(source = "dataCriacao", target = "dataCriacao")
	AlertaDTO toDTO(Alerta alerta);
	
	@Mapping(source = "dataCriacao", target = "dataCriacao")
	Alerta toEntity(AlertaDTO toDTO);
	
}
