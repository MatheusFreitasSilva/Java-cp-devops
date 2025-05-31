package com.gs.alagamenos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.alagamenos.dto.CidadeDTO;
import com.gs.alagamenos.mapper.CidadeMapper;
import com.gs.alagamenos.mapper.CidadeMapperInterface;
import com.gs.alagamenos.model.Cidade;
import com.gs.alagamenos.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repC;
	
	@Autowired
	private CidadeCachingService cacheC;
	
	 @Autowired
	 private CidadeMapper mapper;
	
	@Autowired
	private CidadeMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<CidadeDTO> paginar(PageRequest req){
		
		Page<Cidade> cidades = cacheC.findAll(req);
	
		return cidades.map(i -> mapperInterface.toDTO(i));
	}
	
}
