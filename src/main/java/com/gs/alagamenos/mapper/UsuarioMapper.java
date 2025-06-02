package com.gs.alagamenos.mapper;

import org.springframework.stereotype.Component;

import com.gs.alagamenos.dto.UsuarioDTO;
import com.gs.alagamenos.model.Usuario;

@Component
public class UsuarioMapper {
	
	UsuarioDTO toDTO(Usuario usuario) {
		
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setDataNascimento(usuario.getDataNascimento());
		dto.setEmail(usuario.getEmail());
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		dto.setSenha(usuario.getSenha());
		dto.setTelefone(usuario.getTelefone());
		
		return dto;
	}

}
