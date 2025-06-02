package com.gs.alagamenos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gs.alagamenos.model.UsuarioAlerta;
import com.gs.alagamenos.model.UsuarioAlertaId;
import com.gs.alagamenos.repository.UsuarioAlertaRepository;

@Service
public class UsuarioAlertaCachingService {

	@Autowired
	private UsuarioAlertaRepository repUA;
	
	@Cacheable(value = "buscaTodosOsAlertasDeUsuarios")
	public List<UsuarioAlerta> findAll(){
		
		return repUA.findAll();
	}
	
	@Cacheable(value = "buscaAlertaDeUsuarioPorId")
	public Optional<UsuarioAlerta> findById(UsuarioAlertaId id) {
		return repUA.findById(id);
	}
	
	@Cacheable(value = "buscaAlertaDeUsuarioPaginado")
	public Page<UsuarioAlerta> findAll(PageRequest req){
		return repUA.findAll(req);
	}
	
	@CacheEvict(value = {"buscaTodosOsAlertasDeUsuarios", "buscaAlertaDeUsuarioPorId",
						"buscaAlertaDeUsuarioPaginado"}, allEntries = true)
	public void limparCache() {
		System.out.println("Limpando o cache!");
	}
}
