package com.gs.alagamenos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gs.alagamenos.model.Endereco;
import com.gs.alagamenos.repository.EnderecoRepository;

@Service
public class EnderecoCachingService {

	@Autowired
	private EnderecoRepository repE;
	
	@Cacheable(value = "buscaTodosOsEnderecos")
	public List<Endereco> findAll(){
		
		return repE.findAll();
	}
	
	@Cacheable(value = "buscaEnderecoPorId")
	public Optional<Endereco> findById(Long id) {
		return repE.findById(id);
	}
	
	@Cacheable(value = "buscaEnderecoPaginado")
	public Page<Endereco> findAll(PageRequest req){
		return repE.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodosOsEnderecos", "buscaEnderecoPorId",
						"buscaEnderecoPaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}
}
