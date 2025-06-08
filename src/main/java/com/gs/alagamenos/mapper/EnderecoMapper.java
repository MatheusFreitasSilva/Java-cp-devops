package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.EnderecoDTO;
import com.gs.alagamenos.model.Endereco;

@Component
public class EnderecoMapper {
	
	private final UsuarioMapper usuarioMapper;
	
	public EnderecoMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

	public EnderecoDTO toDTO(Endereco endereco) {
		
		EnderecoDTO dto = new EnderecoDTO();
		
		dto.setId(endereco.getId());
		dto.setComplemento(endereco.getComplemento());
		dto.setNumero_endereco(endereco.getNumero_endereco());
		dto.setRua(endereco.getRua());
		dto.setUsuario(usuarioMapper.toDTO(endereco.getUsuario()));
		
		return dto;
	}
	
}
