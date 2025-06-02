package com.gs.alagamenos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gs.alagamenos.model.Bairro;
import com.gs.alagamenos.repository.BairroRepository;

@Service
public class BairroCachingService {
	
	@Autowired
	private BairroRepository repB;
	
	@Cacheable(value = "buscaTodosOsBairros")
	public List<Bairro> findAll(){
		
		return repB.findAll();
	}
	
	@Cacheable(value = "buscaBairroPorId")
	public Optional<Bairro> findById(Long id) {
		return repB.findById(id);
	}
	
	@Cacheable(value = "buscaBairroPaginado")
	public Page<Bairro> findAll(PageRequest req){
		return repB.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodosOsBairros", "buscaBairroPorId",
						"buscaBairroPaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}

}
