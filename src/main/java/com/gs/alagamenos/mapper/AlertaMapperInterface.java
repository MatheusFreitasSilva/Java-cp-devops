package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.AlertaDTO;
import com.gs.alagamenos.model.Alerta;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AlertaMapperInterface {

	AlertaDTO toDTO(Alerta alerta);
	
	Alerta toEntity(AlertaDTO toDTO);
	
}
