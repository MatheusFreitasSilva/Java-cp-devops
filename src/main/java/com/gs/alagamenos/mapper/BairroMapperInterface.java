package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.BairroDTO;
import com.gs.alagamenos.model.Bairro;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BairroMapperInterface {

	BairroDTO toDTO(Bairro bairro);
	
	Bairro toEntity(BairroDTO toDTO);
	
}
