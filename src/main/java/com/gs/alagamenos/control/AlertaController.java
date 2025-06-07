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
import com.gs.alagamenos.dto.AlertaDTO;
import com.gs.alagamenos.dto.AlertaEnvioDTO;
import com.gs.alagamenos.mapper.AlertaMapperInterface;
import com.gs.alagamenos.model.Alerta;
import com.gs.alagamenos.model.Bairro;
import com.gs.alagamenos.model.Rua;
import com.gs.alagamenos.repository.AlertaRepository;
import com.gs.alagamenos.repository.RuaRepository;
import com.gs.alagamenos.service.AlertaCachingService;
import com.gs.alagamenos.service.AlertaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/alertas")
public class AlertaController {
	
	private final AlagamenosApplication alagamenosApplication;
	
	@Autowired
	private AlertaRepository repA;
	
	@Autowired
	private RuaRepository repR;
	
	@Autowired
	private AlertaService servA;
	
	@Autowired
	private AlertaCachingService cacheA;
	
	@Autowired
	private AlertaMapperInterface mapperInterface;
	
	AlertaController (AlagamenosApplication alagamenosApplication) {
		this.alagamenosApplication = alagamenosApplication;
	}
	
	// GET All paginado
	@Operation(description = "Retorna lista de AlertaDTO de forma paginada", 
			summary = "Retorna páginas de AlertaDTO",
			tags = {"Alerta"})
	@GetMapping(value = "/paginadas")
	public ResponseEntity<Page<AlertaDTO>> retornarAlertasPaginados(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size){
		
		PageRequest req = PageRequest.of(page, size);
		
		Page<AlertaDTO> alertas_paginados = servA.paginar(req);
		
		return ResponseEntity.ok(alertas_paginados);
	}
	
	// GET All
	@Operation(description = "Retorna todos os alertas",
			summary = "Retorna todos os alertas",
			tags = {"Alerta"})
	@GetMapping(value = "/todas")
	public List<AlertaDTO> retornaTodosAlertas(){
		
		return repA.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET all Cacheable
	@Operation(description = "Retorna todos os alertas existentes no Cache",
			summary = "Retorna todos os alertas utilizando Caching",
			tags = {"Alerta"})
	@GetMapping(value = "/todas_cacheable")
	public List<AlertaDTO> retonaTodosAlertasCacheable(){
		
		return cacheA.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET By Id
	@Operation(description = "Retorna um alerta com base em um ID",
			summary = "Retorna um alerta com base em um ID",
			tags = {"Alerta"})
	@GetMapping(value = "/{id}")
	public AlertaDTO retornaAlertaPorId(@PathVariable Long id) {
		
		Optional<Alerta> op = cacheA.findById(id);
		
		if(op.isPresent()) {
			return mapperInterface.toDTO(op.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	// POST
	@Operation(description = "Esta operação possibilita a inserção de um novo item na tabela de alertas",
			summary = "Inserir um novo alerta",
			tags = {"Alerta"})
	@PostMapping(value = "/inserir")
	public ResponseEntity<Alerta> inserirAlerta(@RequestBody @Valid AlertaEnvioDTO dto) {
		
		Rua rua = repR.findById(dto.getRuaId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rua não encontrada"));
		
		Alerta alerta = new Alerta();
		alerta.setDataCriacao(dto.getDataCriacao());
		alerta.setMensagem(dto.getMensagem());
		alerta.setRua(rua);
		
		repA.save(alerta);
		cacheA.limparCache();
				
		return ResponseEntity.ok(alerta);
	}
	
	// PUT
	@Operation(description = "Esta operação possibilita a atualização de um item na tabela de alertas",
			summary = "Atualiza um novo alerta",
			tags = {"Alerta"})
	@PutMapping(value = "/atualizar/{id}")
	public ResponseEntity<Alerta> atualizarAlerta(@PathVariable Long id, @RequestBody AlertaEnvioDTO dto) {
		
		Optional<Alerta> op = cacheA.findById(id);
		
		if(op.isPresent()) {
			Alerta alerta_antigo = op.get();
			
			if (dto.getMensagem() != null) {
				alerta_antigo.setMensagem(dto.getMensagem());
			}
			if (dto.getDataCriacao() != null) {
				alerta_antigo.setDataCriacao(dto.getDataCriacao());
			}
			if (dto.getRuaId() != null) {
				Rua rua = repR.findById(dto.getRuaId())
	                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rua não encontrada"));
	            alerta_antigo.setRua(rua);
			}
			
			repA.save(alerta_antigo);
			cacheA.limparCache();
			
			return ResponseEntity.ok(alerta_antigo);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	// DELETE
	@Operation(description = "Esta operação possibilita a exclusão de um item na tabela de alertas",
			summary = "Exclui um novo alerta",
			tags = {"Alerta"})
	@DeleteMapping(value = "/excluir/{id}")
	public Alerta excluirAlerta(@PathVariable Long id) {
		
		Optional<Alerta> op = repA.findById(id);
		
		if (op.isPresent()) {
			Alerta alerta_remover = op.get();
			repA.delete(alerta_remover);
			cacheA.limparCache();	
			return alerta_remover;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
