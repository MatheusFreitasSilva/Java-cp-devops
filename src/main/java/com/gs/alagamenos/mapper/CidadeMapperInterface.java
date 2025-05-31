package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.CidadeDTO;
import com.gs.alagamenos.model.Cidade;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CidadeMapperInterface {

	CidadeDTO toDTO(Cidade cidade);
	
	Cidade toEntity(CidadeDTO toDTO);
	
}
