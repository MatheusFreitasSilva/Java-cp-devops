package com.gs.alagamenos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.gs.alagamenos.dto.EnderecoDTO;
import com.gs.alagamenos.model.Endereco;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapperInterface {
	
	EnderecoDTO toDTO(Endereco endereco);
	
	Endereco toEntity(EnderecoDTO toDTO);

}

