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
import com.gs.alagamenos.dto.BairroDTO;
import com.gs.alagamenos.mapper.BairroMapperInterface;
import com.gs.alagamenos.model.Bairro;
import com.gs.alagamenos.repository.BairroRepository;
import com.gs.alagamenos.service.BairroCachingService;
import com.gs.alagamenos.service.BairroService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/bairros")
public class BairroController {
	
	private final AlagamenosApplication alagamenosApplication;
	
	@Autowired
	private BairroRepository repB;
	
	@Autowired
	private BairroService servB;
	
	@Autowired
	private BairroCachingService cacheB;
	
	@Autowired
	private BairroMapperInterface mapperInterface;
	
	BairroController (AlagamenosApplication alagamenosApplication) {
		this.alagamenosApplication = alagamenosApplication;
	}
	
	// GET All paginado
	@Operation(description = "Retorna lista de BairroDTO de forma paginada", 
			summary = "Retorna páginas de BairroDTO",
			tags = "Retorno de informação")
	@GetMapping(value = "/paginadas")
	public ResponseEntity<Page<BairroDTO>> retornarBairrosPaginados(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size){
		
		PageRequest req = PageRequest.of(page, size);
		
		Page<BairroDTO> bairros_paginados = servB.paginar(req);
		
		return ResponseEntity.ok(bairros_paginados);
	}
	
	// GET All
	@Operation(description = "Retorna todos os bairros",
			summary = "Retorna todos os bairros",
			tags = "Retorno de informação")
	@GetMapping(value = "/todas")
	public List<BairroDTO> retornaTodosBairros(){
		
		return repB.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET all Cacheable
	@Operation(description = "Retorna todos os bairros existentes no Cache",
			summary = "Retorna todos os bairros utilizando Caching",
			tags = "Retorno de informação")
	@GetMapping(value = "/todas_cacheable")
	public List<BairroDTO> retonaTodosBairrosCacheable(){
		
		return cacheB.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET By Id
	@Operation(description = "Retorna um bairro com base em um ID",
			summary = "Retorna um bairro com base em um ID",
			tags = "Retorno de informação")
	@GetMapping(value = "/{id}")
	public BairroDTO retornaBairroPorId(@PathVariable Long id) {
		
		Optional<Bairro> op = cacheB.findById(id);
		
		if(op.isPresent()) {
			return mapperInterface.toDTO(op.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	// POST
	@Operation(description = "Esta operação possibilita a inserção de um novo item na tabela de bairros",
			summary = "Inserir um novo bairro",
			tags = "Inserção de informações")
	@PostMapping(value = "/inserir")
	public ResponseEntity<Bairro> inserirBairro(@RequestBody @Valid Bairro bairro) {
		
		repB.save(bairro);
		cacheB.limparCache();
				
		return ResponseEntity.ok(bairro);
	}
	
	// PUT
	@Operation(description = "Esta operação possibilita a atualização de um item na tabela de bairros",
			summary = "Atualiza um novo bairro",
			tags = "Inserção de informações")
	@PutMapping(value = "/atualizar/{id}")
	public Bairro atualizarBairro(@PathVariable Long id, @RequestBody Bairro bairro) {
		
		Optional<Bairro> op = cacheB.findById(id);
		
		if(op.isPresent()) {
			Bairro bairro_antigo = op.get();
			bairro_antigo.setNome_bairro(bairro.getNome_bairro());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return bairro;
	}
	
	// DELETE
	@Operation(description = "Esta operação possibilita a exclusão de um item na tabela de bairros",
			summary = "Exclui um novo bairro",
			tags = "Remoção de informações")
	@DeleteMapping(value = "/excluir/{id}")
	public Bairro excluirBairro(@PathVariable Long id) {
		
		Optional<Bairro> op = repB.findById(id);
		
		if (op.isPresent()) {
			Bairro bairro_remover = op.get();
			repB.delete(bairro_remover);
			cacheB.limparCache();	
			return bairro_remover;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
