package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.EstadoDTO;
import com.gs.alagamenos.model.Estado;

@Component
public class EstadoMapper {

	public EstadoDTO toDTO(Estado estado) {
		
		EstadoDTO dto = new EstadoDTO();
		
		dto.setId(estado.getId());
		dto.setNome_estado(estado.getNome_estado());
		
		return dto;
	}
	
}
