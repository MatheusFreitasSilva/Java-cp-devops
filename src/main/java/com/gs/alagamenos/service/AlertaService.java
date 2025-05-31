package com.gs.alagamenos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.alagamenos.dto.AlertaDTO;
import com.gs.alagamenos.mapper.AlertaMapper;
import com.gs.alagamenos.mapper.AlertaMapperInterface;
import com.gs.alagamenos.model.Alerta;
import com.gs.alagamenos.repository.AlertaRepository;

@Service
public class AlertaService {

	@Autowired
	private AlertaRepository repA;
	
	@Autowired
	private AlertaCachingService cacheA;
	
	 @Autowired
	 private AlertaMapper mapper;
	
	@Autowired
	private AlertaMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<AlertaDTO> paginar(PageRequest req){
		
		Page<Alerta> alertas = cacheA.findAll(req);
	
		return alertas.map(i -> mapperInterface.toDTO(i));
	}
}
