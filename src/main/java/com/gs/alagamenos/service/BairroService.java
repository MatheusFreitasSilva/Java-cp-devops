package com.gs.alagamenos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.alagamenos.dto.BairroDTO;
import com.gs.alagamenos.mapper.BairroMapper;
import com.gs.alagamenos.mapper.BairroMapperInterface;
import com.gs.alagamenos.model.Bairro;
import com.gs.alagamenos.repository.BairroRepository;

@Service
public class BairroService {
	
	@Autowired
	private BairroRepository repB;
	
	@Autowired
	private BairroCachingService cacheB;
	
	 @Autowired
	 private BairroMapper mapper;
	
	@Autowired
	private BairroMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<BairroDTO> paginar(PageRequest req){
		
		Page<Bairro> bairros = cacheB.findAll(req);
	
		return bairros.map(i -> mapperInterface.toDTO(i));
	}

}
