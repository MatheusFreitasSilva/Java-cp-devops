package com.gs.alagamenos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gs.alagamenos.model.Cidade;
import com.gs.alagamenos.repository.CidadeRepository;

@Service
public class CidadeCachingService {
	
	@Autowired
	private CidadeRepository repC;
	
	@Cacheable(value = "buscaTodosAsCidades")
	public List<Cidade> findAll(){
		
		return repC.findAll();
	}
	
	@Cacheable(value = "buscaCidadePorId")
	public Optional<Cidade> findById(Long id) {
		return repC.findById(id);
	}
	
	@Cacheable(value = "buscaCidadePaginado")
	public Page<Cidade> findAll(PageRequest req){
		return repC.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodosAsCidades", "buscaCidadePorId",
						"buscaCidadePaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}
	
}
