package com.gs.alagamenos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.alagamenos.dto.UsuarioAlertaDTO;
import com.gs.alagamenos.mapper.UsuarioAlertaMapper;
import com.gs.alagamenos.mapper.UsuarioAlertaMapperInterface;
import com.gs.alagamenos.model.UsuarioAlerta;
import com.gs.alagamenos.repository.UsuarioAlertaRepository;

@Service
public class UsuarioAlertaService {
	
	@Autowired
	private UsuarioAlertaRepository repUA;
	
	@Autowired
	private UsuarioAlertaCachingService cacheUA;
	
	 @Autowired
	 private UsuarioAlertaMapper mapper;
	
	@Autowired
	private UsuarioAlertaMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<UsuarioAlertaDTO> paginar(PageRequest req){
		
		Page<UsuarioAlerta> usuariosAlertas = cacheUA.findAll(req);
	
		return usuariosAlertas.map(i -> mapperInterface.toDTO(i));
	}

}
