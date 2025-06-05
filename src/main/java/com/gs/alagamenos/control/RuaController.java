package com.gs.alagamenos.control;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gs.alagamenos.AlagamenosApplication;
import com.gs.alagamenos.dto.RuaDTO;
import com.gs.alagamenos.mapper.RuaMapperInterface;
import com.gs.alagamenos.model.Rua;
import com.gs.alagamenos.repository.RuaRepository;
import com.gs.alagamenos.service.RuaCachingService;
import com.gs.alagamenos.service.RuaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/ruas")
public class RuaController {
	
	private final AlagamenosApplication alagamenosApplication;
	
	@Autowired
	private RuaRepository repR;
	
	@Autowired
	private RuaService servR;
	
	@Autowired
	private RuaCachingService cacheR;
	
	@Autowired
	private RuaMapperInterface mapperInterface;
	
	RuaController (AlagamenosApplication alagamenosApplication) {
		this.alagamenosApplication = alagamenosApplication;
	}
	
	// GET All paginado
	@Operation(description = "Retorna lista de RuaDTO de forma paginada", 
			summary = "Retorna páginas de RuaDTO",
			tags = {"Rua"})
	@GetMapping(value = "/paginadas")
	public ResponseEntity<Page<RuaDTO>> retornarRuasPaginados(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size){
		
		PageRequest req = PageRequest.of(page, size);
		
		Page<RuaDTO> ruas_paginadas = servR.paginar(req);
		
		return ResponseEntity.ok(ruas_paginadas);
	}
	
	// GET All
	@Operation(description = "Retorna todas as ruas",
			summary = "Retorna todos as ruas",
			tags = {"Rua"})
	@GetMapping(value = "/todas")
	public List<RuaDTO> retornaTodasRuas(){
		
		return repR.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET all Cacheable
	@Operation(description = "Retorna todas as ruas existentes no Cache",
			summary = "Retorna todas as ruas utilizando Caching",
			tags = {"Rua"})
	@GetMapping(value = "/todas_cacheable")
	public List<RuaDTO> retonaTodasRuasCacheable(){
		
		return cacheR.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET By Id
	@Operation(description = "Retorna uma rua com base em um ID",
			summary = "Retorna uma rua com base em um ID",
			tags = {"Rua"})
	@GetMapping(value = "/{id}")
	public RuaDTO retornaRuaPorId(@PathVariable Long id) {
		
		Optional<Rua> op = cacheR.findById(id);
		
		if(op.isPresent()) {
			return mapperInterface.toDTO(op.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	// POST
	@Operation(description = "Esta operação possibilita a inserção de um novo item na tabela de ruas",
			summary = "Inserir uma nova rua",
			tags = {"Rua"})
	@PostMapping(value = "/inserir")
	public ResponseEntity<Rua> inserirRua(@RequestBody @Valid Rua rua) {
		
		repR.save(rua);
		cacheR.limparCache();
				
		return ResponseEntity.ok(rua);
	}
	
	// PUT
	@Operation(description = "Esta operação possibilita a atualização de um item na tabela de ruas",
			summary = "Atualiza uma nova rua",
			tags = {"Rua"})
	@PutMapping(value = "/atualizar/{id}")
	public Rua atualizarRua(@PathVariable Long id, @RequestBody Rua rua) {
		
		Optional<Rua> op = cacheR.findById(id);
		
		if(op.isPresent()) {
			Rua rua_antiga = op.get();
			rua_antiga.setNome_rua(rua.getNome_rua());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return rua;
	}
	
	// DELETE
	@Operation(description = "Esta operação possibilita a exclusão de um item na tabela de ruas",
			summary = "Exclui uma nova rua",
			tags = {"Rua"})
	@DeleteMapping(value = "/excluir/{id}")
	public Rua excluirRua(@PathVariable Long id) {
		
		Optional<Rua> op = repR.findById(id);
		
		if (op.isPresent()) {
			Rua rua_remover = op.get();
			repR.delete(rua_remover);
			cacheR.limparCache();	
			return rua_remover;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

} 