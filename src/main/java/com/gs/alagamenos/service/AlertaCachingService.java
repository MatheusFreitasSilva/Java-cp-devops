package com.gs.alagamenos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gs.alagamenos.model.Alerta;
import com.gs.alagamenos.repository.AlertaRepository;

@Service
public class AlertaCachingService {
	
	private AlertaRepository repA;
	
	@Cacheable(value = "buscaTodosOsAlertas")
	public List<Alerta> findAll(){
		
		return repA.findAll();
	}
	
	@Cacheable(value = "buscaAlertaPorId")
	public Optional<Alerta> findById(Long id) {
		return repA.findById(id);
	}
	
	@Cacheable(value = "buscaAlertaPaginado")
	public Page<Alerta> findAll(PageRequest req){
		return repA.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodosOsAlertas", "buscaAlertaPorId",
						"buscaAlertaPaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}

}
