package com.gs.alagamenos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.alagamenos.dto.UsuarioDTO;
import com.gs.alagamenos.mapper.UsuarioMapper;
import com.gs.alagamenos.mapper.UsuarioMapperInterface;
import com.gs.alagamenos.model.Usuario;
import com.gs.alagamenos.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repU;
	
	@Autowired
	private UsuarioCachingService cacheU;
	
	 @Autowired
	 private UsuarioMapper mapper;
	
	@Autowired
	private UsuarioMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> paginar(PageRequest req){
		
		Page<Usuario> usuarios = cacheU.findAll(req);
	
		return usuarios.map(i -> mapperInterface.toDTO(i));
	}

}
