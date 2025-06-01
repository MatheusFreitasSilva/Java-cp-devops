package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.EstadoDTO;
import com.gs.alagamenos.model.Estado;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EstadoMapperInterface {
	
	EstadoDTO toDTO(Estado estado);
	
	Estado toEntity(EstadoDTO toDTO);

}

