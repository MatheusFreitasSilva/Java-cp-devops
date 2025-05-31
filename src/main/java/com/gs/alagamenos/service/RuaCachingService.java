package com.gs.alagamenos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.gs.alagamenos.model.Rua;
import com.gs.alagamenos.repository.RuaRepository;

public class RuaCachingService {
	
	private RuaRepository repR;
	
	@Cacheable(value = "buscaTodasAsRuas")
	public List<Rua> findAll(){
		
		return repR.findAll();
	}
	
	@Cacheable(value = "buscaRuaPorId")
	public Optional<Rua> findById(Long id) {
		return repR.findById(id);
	}
	
	@Cacheable(value = "buscaRuaPaginado")
	public Page<Rua> findAll(PageRequest req){
		return repR.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodasAsRuas", "buscaRuaPorId",
						"buscaRuaPaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}

}
