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
import com.gs.alagamenos.dto.EstadoDTO;
import com.gs.alagamenos.mapper.EstadoMapperInterface;
import com.gs.alagamenos.model.Estado;
import com.gs.alagamenos.repository.EstadoRepository;
import com.gs.alagamenos.service.EstadoCachingService;
import com.gs.alagamenos.service.EstadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/estados")
public class EstadoController {
	
	private final AlagamenosApplication alagamenosApplication;
	
	@Autowired
	private EstadoRepository repE;
	
	@Autowired
	private EstadoService servE;
	
	@Autowired
	private EstadoCachingService cacheE;
	
	@Autowired
	private EstadoMapperInterface mapperInterface;
	
	EstadoController (AlagamenosApplication alagamenosApplication) {
		this.alagamenosApplication = alagamenosApplication;
	}
	
	// GET All paginado
	@Operation(description = "Retorna lista de EstadoDTO de forma paginada", 
			summary = "Retorna páginas de EstadoDTO",
			tags = {"Estado"})
	@GetMapping(value = "/paginadas")
	public ResponseEntity<Page<EstadoDTO>> retornarEstadosPaginados(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size){
		
		PageRequest req = PageRequest.of(page, size);
		
		Page<EstadoDTO> estados_paginados = servE.paginar(req);
		
		return ResponseEntity.ok(estados_paginados);
	}
	
	// GET All
	@Operation(description = "Retorna todos os estados",
			summary = "Retorna todos os estados",
			tags = {"Estado"})
	@GetMapping(value = "/todas")
	public List<EstadoDTO> retornaTodosEstados(){
		
		return repE.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET all Cacheable
	@Operation(description = "Retorna todos os estados existentes no Cache",
			summary = "Retorna todos os estados utilizando Caching",
			tags = {"Estado"})
	@GetMapping(value = "/todas_cacheable")
	public List<EstadoDTO> retonaTodosEstadosCacheable(){
		
		return cacheE.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET By Id
	@Operation(description = "Retorna um estado com base em um ID",
			summary = "Retorna um estado com base em um ID",
			tags = {"Estado"})
	@GetMapping(value = "/{id}")
	public EstadoDTO retornaEstadoPorId(@PathVariable Long id) {
		
		Optional<Estado> op = cacheE.findById(id);
		
		if(op.isPresent()) {
			return mapperInterface.toDTO(op.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	// POST
	@Operation(description = "Esta operação possibilita a inserção de um novo item na tabela de estados",
			summary = "Inserir um novo estado",
			tags = {"Estado"})
	@PostMapping(value = "/inserir")
	public ResponseEntity<Estado> inserirEstado(@RequestBody @Valid Estado estado) {
		
		repE.save(estado);
		cacheE.limparCache();
				
		return ResponseEntity.ok(estado);
	}
	
	// PUT
	@Operation(description = "Esta operação possibilita a atualização de um item na tabela de estados",
			summary = "Atualiza um novo estado",
			tags = {"Estado"})
	@PutMapping(value = "/atualizar/{id}")
	public Estado atualizarEstado(@PathVariable Long id, @RequestBody Estado estado) {
		
		Optional<Estado> op = cacheE.findById(id);
		
		if(op.isPresent()) {
			Estado estado_antigo = op.get();
			estado_antigo.setNome_estado(estado.getNome_estado());
			
			repE.save(estado_antigo);
			cacheE.limparCache();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return estado;
	}
	
	// DELETE
	@Operation(description = "Esta operação possibilita a exclusão de um item na tabela de estados",
			summary = "Exclui um novo estado",
			tags = {"Estado"})
	@DeleteMapping(value = "/excluir/{id}")
	public Estado excluirEstado(@PathVariable Long id) {
		
		Optional<Estado> op = repE.findById(id);
		
		if (op.isPresent()) {
			Estado estado_remover = op.get();
			repE.delete(estado_remover);
			cacheE.limparCache();	
			return estado_remover;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
