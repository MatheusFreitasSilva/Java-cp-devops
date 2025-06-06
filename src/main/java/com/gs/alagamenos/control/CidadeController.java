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
import com.gs.alagamenos.dto.CidadeDTO;
import com.gs.alagamenos.mapper.CidadeMapperInterface;
import com.gs.alagamenos.model.Cidade;
import com.gs.alagamenos.repository.CidadeRepository;
import com.gs.alagamenos.service.CidadeCachingService;
import com.gs.alagamenos.service.CidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {
	
	private final AlagamenosApplication alagamenosApplication;
	
	@Autowired
	private CidadeRepository repC;
	
	@Autowired
	private CidadeService servC;
	
	@Autowired
	private CidadeCachingService cacheC;
	
	@Autowired
	private CidadeMapperInterface mapperInterface;
	
	CidadeController (AlagamenosApplication alagamenosApplication) {
		this.alagamenosApplication = alagamenosApplication;
	}
	
	// GET All paginado
	@Operation(description = "Retorna lista de CidadeDTO de forma paginada", 
			summary = "Retorna páginas de CidadeDTO",
			tags = {"Cidade"})
	@GetMapping(value = "/paginadas")
	public ResponseEntity<Page<CidadeDTO>> retornarCidadesPaginados(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size){
		
		PageRequest req = PageRequest.of(page, size);
		
		Page<CidadeDTO> cidades_paginadas = servC.paginar(req);
		
		return ResponseEntity.ok(cidades_paginadas);
	}
	
	// GET All
	@Operation(description = "Retorna todas as cidades",
			summary = "Retorna todos as cidades",
			tags = {"Cidade"})
	@GetMapping(value = "/todas")
	public List<CidadeDTO> retornaTodasCidades(){
		
		return repC.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET all Cacheable
	@Operation(description = "Retorna todas as cidades existentes no Cache",
			summary = "Retorna todas as cidades utilizando Caching",
			tags = {"Cidade"})
	@GetMapping(value = "/todas_cacheable")
	public List<CidadeDTO> retonaTodasCidadesCacheable(){
		
		return cacheC.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET By Id
	@Operation(description = "Retorna uma cidade com base em um ID",
			summary = "Retorna uma cidade com base em um ID",
			tags = {"Cidade"})
	@GetMapping(value = "/{id}")
	public CidadeDTO retornaCidadePorId(@PathVariable Long id) {
		
		Optional<Cidade> op = cacheC.findById(id);
		
		if(op.isPresent()) {
			return mapperInterface.toDTO(op.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	// POST
	@Operation(description = "Esta operação possibilita a inserção de um novo item na tabela de cidades",
			summary = "Inserir uma nova cidade",
			tags = {"Cidade"})
	@PostMapping(value = "/inserir")
	public ResponseEntity<Cidade> inserirCidade(@RequestBody @Valid Cidade cidade) {
		
		repC.save(cidade);
		cacheC.limparCache();
				
		return ResponseEntity.ok(cidade);
	}
	
	// PUT
	@Operation(description = "Esta operação possibilita a atualização de um item na tabela de cidades",
			summary = "Atualiza uma nova cidade",
			tags = {"Cidade"})
	@PutMapping(value = "/atualizar/{id}")
	public Cidade atualizarCidade(@PathVariable Long id, @RequestBody Cidade cidade) {
		
		Optional<Cidade> op = cacheC.findById(id);
		
		if(op.isPresent()) {
			Cidade cidade_antiga = op.get();
			cidade_antiga.setNome_cidade(cidade.getNome_cidade());
			
			repC.save(cidade_antiga);
			cacheC.limparCache();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return cidade;
	}
	
	// DELETE
	@Operation(description = "Esta operação possibilita a exclusão de um item na tabela de cidades",
			summary = "Exclui uma nova cidade",
			tags = {"Cidade"})
	@DeleteMapping(value = "/excluir/{id}")
	public Cidade excluirCidade(@PathVariable Long id) {
		
		Optional<Cidade> op = repC.findById(id);
		
		if (op.isPresent()) {
			Cidade cidade_remover = op.get();
			repC.delete(cidade_remover);
			cacheC.limparCache();	
			return cidade_remover;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}