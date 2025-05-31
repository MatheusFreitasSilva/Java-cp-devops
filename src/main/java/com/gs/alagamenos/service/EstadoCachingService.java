package com.gs.alagamenos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gs.alagamenos.model.Estado;
import com.gs.alagamenos.repository.EstadoRepository;

@Service
public class EstadoCachingService {

	private EstadoRepository repE;
	
	@Cacheable(value = "buscaTodosOsEstados")
	public List<Estado> findAll(){
		
		return repE.findAll();
	}
	
	@Cacheable(value = "buscaEstadoPorId")
	public Optional<Estado> findById(Long id) {
		return repE.findById(id);
	}
	
	@Cacheable(value = "buscaEstadoPaginado")
	public Page<Estado> findAll(PageRequest req){
		return repE.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodosOsEstados", "buscaEstadoPorId",
						"buscaEstadoPaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}
}
