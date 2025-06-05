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
import com.gs.alagamenos.dto.EnderecoDTO;
import com.gs.alagamenos.mapper.EnderecoMapperInterface;
import com.gs.alagamenos.model.Endereco;
import com.gs.alagamenos.repository.EnderecoRepository;
import com.gs.alagamenos.service.EnderecoCachingService;
import com.gs.alagamenos.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {
	
	private final AlagamenosApplication alagamenosApplication;
	
	@Autowired
	private EnderecoRepository repE;
	
	@Autowired
	private EnderecoService servE;
	
	@Autowired
	private EnderecoCachingService cacheE;
	
	@Autowired
	private EnderecoMapperInterface mapperInterface;
	
	EnderecoController (AlagamenosApplication alagamenosApplication) {
		this.alagamenosApplication = alagamenosApplication;
	}
	
	// GET All paginado
	@Operation(description = "Retorna lista de EnderecoDTO de forma paginada", 
			summary = "Retorna páginas de EnderecoDTO",
			tags = {"Endereço"})
	@GetMapping(value = "/paginadas")
	public ResponseEntity<Page<EnderecoDTO>> retornarEnderecosPaginados(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size){
		
		PageRequest req = PageRequest.of(page, size);
		
		Page<EnderecoDTO> enderecos_paginados = servE.paginar(req);
		
		return ResponseEntity.ok(enderecos_paginados);
	}
	
	// GET All
	@Operation(description = "Retorna todos os enderecos",
			summary = "Retorna todos os enderecos",
			tags = {"Endereço"})
	@GetMapping(value = "/todas")
	public List<EnderecoDTO> retornaTodosEnderecos(){
		
		return repE.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET all Cacheable
	@Operation(description = "Retorna todos os enderecos existentes no Cache",
			summary = "Retorna todos os enderecos utilizando Caching",
			tags = {"Endereço"})
	@GetMapping(value = "/todas_cacheable")
	public List<EnderecoDTO> retonaTodosEnderecosCacheable(){
		
		return cacheE.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET By Id
	@Operation(description = "Retorna um endereco com base em um ID",
			summary = "Retorna um endereco com base em um ID",
			tags = {"Endereço"})
	@GetMapping(value = "/{id}")
	public EnderecoDTO retornaEnderecoPorId(@PathVariable Long id) {
		
		Optional<Endereco> op = cacheE.findById(id);
		
		if(op.isPresent()) {
			return mapperInterface.toDTO(op.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	// POST
	@Operation(description = "Esta operação possibilita a inserção de um novo item na tabela de enderecos",
			summary = "Inserir um novo endereco",
			tags = {"Endereço"})
	@PostMapping(value = "/inserir")
	public ResponseEntity<Endereco> inserirEndereco(@RequestBody @Valid Endereco endereco) {
		
		repE.save(endereco);
		cacheE.limparCache();
				
		return ResponseEntity.ok(endereco);
	}
	
	// PUT
	@Operation(description = "Esta operação possibilita a atualização de um item na tabela de enderecos",
			summary = "Atualiza um novo endereco",
			tags = {"Endereço"})
	@PutMapping(value = "/atualizar/{id}")
	public Endereco atualizarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
		
		Optional<Endereco> op = cacheE.findById(id);
		
		if(op.isPresent()) {
			Endereco endereco_antigo = op.get();
			endereco_antigo.setComplemento(endereco.getComplemento());
			endereco_antigo.setNumero_endereco(endereco.getNumero_endereco());
			endereco_antigo.setRua(endereco.getRua());
			endereco_antigo.setUsuario(endereco.getUsuario());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return endereco;
	}
	
	// DELETE
	@Operation(description = "Esta operação possibilita a exclusão de um item na tabela de enderecos",
			summary = "Exclui um novo endereco",
			tags = {"Endereço"})
	@DeleteMapping(value = "/excluir/{id}")
	public Endereco excluirEndereco(@PathVariable Long id) {
		
		Optional<Endereco> op = repE.findById(id);
		
		if (op.isPresent()) {
			Endereco endereco_remover = op.get();
			repE.delete(endereco_remover);
			cacheE.limparCache();	
			return endereco_remover;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
