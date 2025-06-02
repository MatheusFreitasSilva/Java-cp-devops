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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gs.alagamenos.AlagamenosApplication;
import com.gs.alagamenos.dto.UsuarioAlertaDTO;
import com.gs.alagamenos.mapper.UsuarioAlertaMapperInterface;
import com.gs.alagamenos.model.UsuarioAlerta;
import com.gs.alagamenos.model.UsuarioAlertaId;
import com.gs.alagamenos.repository.UsuarioAlertaRepository;
import com.gs.alagamenos.service.UsuarioAlertaCachingService;
import com.gs.alagamenos.service.UsuarioAlertaService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/alertas_usuarios")
public class UsuarioAlertaController {
	
	private final AlagamenosApplication alagamenosApplication;
	
	@Autowired
	private UsuarioAlertaRepository repUA;
	
	@Autowired
	private UsuarioAlertaService servUA;
	
	@Autowired
	private UsuarioAlertaCachingService cacheUA;
	
	@Autowired
	private UsuarioAlertaMapperInterface mapperInterface;
	
	UsuarioAlertaController (AlagamenosApplication alagamenosApplication) {
		this.alagamenosApplication = alagamenosApplication;
	}
	
	// GET All paginado
	@Operation(description = "Retorna lista de UsuariAlertaoDTO de forma paginada", 
			summary = "Retorna páginas de UsuarioAlertaDTO",
			tags = "Alertas de usuário - Retorno de informações")
	@GetMapping(value = "/paginadas")
	public ResponseEntity<Page<UsuarioAlertaDTO>> retornarUsuariosAlertasPaginados(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size){
		
		PageRequest req = PageRequest.of(page, size);
		
		Page<UsuarioAlertaDTO> usuarios_alertas_paginados = servUA.paginar(req);
		
		return ResponseEntity.ok(usuarios_alertas_paginados);
	}
	
	// GET All
	@Operation(description = "Retorna todos os alertas de usuarios",
			summary = "Retorna todos os alertas de usuarios",
			tags = "Alertas de usuário - Retorno de informações")
	@GetMapping(value = "/todas")
	public List<UsuarioAlertaDTO> retornaTodosUsuariosAlertas(){
		
		return repUA.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET all Cacheable
	@Operation(description = "Retorna todos os usuarios existentes no Cache",
			summary = "Retorna todos os usuarios utilizando Caching",
			tags = "Alertas de usuário - Retorno de informações")
	@GetMapping(value = "/todas_cacheable")
	public List<UsuarioAlertaDTO> retonaTodosUsuariosCacheable(){
		
		return cacheUA.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET By Id
	@Operation(description = "Retorna um alerta de usuario com base no ID de um usuário e no ID de um Alerta",
			summary = "Retorna um alerta de usuario com base em um ID composto (Id de Usuario e Id de alerta)",
			tags = "Alertas de usuário - Retorno de informações")
	@GetMapping(value = "/{usuarioiId}/{alertaId}")
	public UsuarioAlertaDTO retornaUsuarioPorId(@PathVariable Long usuarioId, @PathVariable Long alertaId) {
		
		UsuarioAlertaId id = new UsuarioAlertaId(usuarioId, alertaId);
		
		Optional<UsuarioAlerta> op = cacheUA.findById(id);
		
		if(op.isPresent()) {
			return mapperInterface.toDTO(op.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	// POST
	@Operation(description = "Esta operação possibilita a inserção de um novo item na tabela de alerta de usuarios",
			summary = "Inserir um novo alerta de usuario",
			tags = "Alertas de usuário - Inserção de informações")
	@PostMapping(value = "/inserir")
	public ResponseEntity<UsuarioAlerta> inserirUsuarioAlerta(@RequestBody @Valid UsuarioAlerta usuarioAlerta) {
		
		repUA.save(usuarioAlerta);
		cacheUA.limparCache();
				
		return ResponseEntity.ok(usuarioAlerta);
	}
	
	// DELETE
	@Operation(description = "Esta operação possibilita a exclusão de um item na tabela de usuarios",
			summary = "Exclui um novo usuario",
			tags = "Alertas de usuário - Remoção de informações")
	@DeleteMapping(value = "/excluir/{id}")
	public UsuarioAlerta excluirAlertaUsuario(@PathVariable Long usuarioId, @PathVariable Long alertaId) {
		
		UsuarioAlertaId id = new UsuarioAlertaId(usuarioId, alertaId);
		
		Optional<UsuarioAlerta> op = repUA.findById(id);
		
		if (op.isPresent()) {
			UsuarioAlerta usuario_alerta_remover = op.get();
			repUA.delete(usuario_alerta_remover);
			cacheUA.limparCache();	
			return usuario_alerta_remover;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
