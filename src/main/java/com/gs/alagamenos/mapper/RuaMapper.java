package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.RuaDTO;
import com.gs.alagamenos.model.Rua;

@Component
public class RuaMapper {

	public RuaDTO toDTO (Rua rua) {
		
		RuaDTO dto = new RuaDTO();
		
		dto.setId(rua.getId());
		dto.setBairro(rua.getBairro());
		dto.setNome_rua(rua.getNome_rua());
		dto.setObservacao(rua.getObservacao());
		
		return dto;
	}
	
}
