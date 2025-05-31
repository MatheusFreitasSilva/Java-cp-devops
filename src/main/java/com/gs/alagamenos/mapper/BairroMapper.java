package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.BairroDTO;
import com.gs.alagamenos.model.Bairro;

@Component
public class BairroMapper {

	public BairroDTO toDTO(Bairro bairro) {
		
		BairroDTO dto = new BairroDTO();
		
		dto.setId(bairro.getId());
		dto.setNome_bairro(bairro.getNome_bairro());
		dto.setCidade(bairro.getCidade());
		
		return dto;
	}
	
}
