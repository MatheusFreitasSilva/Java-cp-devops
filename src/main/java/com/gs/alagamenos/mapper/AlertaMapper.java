package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.AlertaDTO;
import com.gs.alagamenos.model.Alerta;

@Component
public class AlertaMapper {

	public AlertaDTO toDTO(Alerta alerta) {
		
		AlertaDTO dto = new AlertaDTO();
		
		dto.setId(alerta.getId());
		dto.setMensagem(alerta.getMensagem());
		dto.setDataCriacao(alerta.getDataCriacao());
		
		return dto;
	}
	
}
