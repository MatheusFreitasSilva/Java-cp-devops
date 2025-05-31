package com.gs.alagamenos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.alagamenos.dto.RuaDTO;
import com.gs.alagamenos.mapper.RuaMapper;
import com.gs.alagamenos.mapper.RuaMapperInterface;
import com.gs.alagamenos.model.Rua;
import com.gs.alagamenos.repository.RuaRepository;

@Service
public class RuaService {
	
	@Autowired
	private RuaRepository repR;
	
	@Autowired
	private RuaCachingService cacheR;
	
	 @Autowired
	 private RuaMapper mapper;
	
	@Autowired
	private RuaMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<RuaDTO> paginar(PageRequest req){
		
		Page<Rua> ruas = cacheR.findAll(req);
	
		return ruas.map(i -> mapperInterface.toDTO(i));
	}

}
