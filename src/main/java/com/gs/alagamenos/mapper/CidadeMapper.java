package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.CidadeDTO;
import com.gs.alagamenos.model.Cidade;

@Component
public class CidadeMapper {

	public CidadeDTO toDTO(Cidade cidade) {
		
		CidadeDTO dto = new CidadeDTO();
		
		dto.setId(cidade.getId());
		dto.setEstado(cidade.getEstado());
		dto.setNome_cidade(cidade.getNome_cidade());
		
		return dto;
		
	}
	
}
