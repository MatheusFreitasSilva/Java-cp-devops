package com.gs.alagamenos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.alagamenos.dto.EnderecoDTO;
import com.gs.alagamenos.mapper.EnderecoMapper;
import com.gs.alagamenos.mapper.EnderecoMapperInterface;
import com.gs.alagamenos.model.Endereco;
import com.gs.alagamenos.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repE;
	
	@Autowired
	private EnderecoCachingService cacheE;
	
	 @Autowired
	 private EnderecoMapper mapper;
	
	@Autowired
	private EnderecoMapperInterface mapperInterface;
	
	@Transactional(readOnly = true)
	public Page<EnderecoDTO> paginar(PageRequest req){
		
		Page<Endereco> enderecos = cacheE.findAll(req);
	
		return enderecos.map(i -> mapperInterface.toDTO(i));
	}
}
