package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.EnderecoDTO;
import com.gs.alagamenos.model.Endereco;

@Component
public class EnderecoMapper {

	public EnderecoDTO toDTO(Endereco endereco) {
		
		EnderecoDTO dto = new EnderecoDTO();
		
		dto.setId(endereco.getId());
		dto.setComplemento(endereco.getComplemento());
		dto.setNumero_endereco(endereco.getNumero_endereco());
		dto.setRua(endereco.getRua());
		dto.setUsuario(endereco.getUsuario());
		
		return dto;
	}
	
}
