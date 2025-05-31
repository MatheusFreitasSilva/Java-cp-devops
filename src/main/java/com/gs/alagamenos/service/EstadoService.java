package com.gs.alagamenos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.alagamenos.dto.EstadoDTO;
import com.gs.alagamenos.mapper.EstadoMapper;
import com.gs.alagamenos.mapper.EstadoMapperInterface;
import com.gs.alagamenos.model.Estado;
import com.gs.alagamenos.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repE;
	
	@Autowired
	private EstadoCachingService cacheE;
	
	 @Autowired
	 private EstadoMapper mapper;
	
	@Autowired
	private EstadoMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<EstadoDTO> paginar(PageRequest req){
		
		Page<Estado> estados = cacheE.findAll(req);
	
		return estados.map(i -> mapperInterface.toDTO(i));
	}
}
