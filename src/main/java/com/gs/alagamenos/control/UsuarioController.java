package com.gs.alagamenos.control;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.gs.alagamenos.dto.UsuarioDTO;
import com.gs.alagamenos.mapper.UsuarioMapperInterface;
import com.gs.alagamenos.model.Usuario;
import com.gs.alagamenos.repository.UsuarioRepository;
import com.gs.alagamenos.service.UsuarioCachingService;
import com.gs.alagamenos.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	private final AlagamenosApplication alagamenosApplication;
	
	@Autowired
	private UsuarioRepository repU;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioService servU;
	
	@Autowired
	private UsuarioCachingService cacheU;
	
	@Autowired
	private UsuarioMapperInterface mapperInterface;
	
	UsuarioController (AlagamenosApplication alagamenosApplication) {
		this.alagamenosApplication = alagamenosApplication;
	}
	
	// GET All paginado
	@Operation(description = "Retorna lista de UsuarioDTO de forma paginada", 
			summary = "Retorna páginas de UsuarioDTO",
			tags = {"Usuário"})
	@GetMapping(value = "/paginadas")
	public ResponseEntity<Page<UsuarioDTO>> retornarUsuariosPaginados(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size){
		
		PageRequest req = PageRequest.of(page, size);
		
		Page<UsuarioDTO> usuarios_paginados = servU.paginar(req);
		
		return ResponseEntity.ok(usuarios_paginados);
	}
	
	// GET All
	@Operation(description = "Retorna todos os usuarios",
			summary = "Retorna todos os usuarios",
			tags = {"Usuário"})
	@GetMapping(value = "/todas")
	public List<UsuarioDTO> retornaTodosUsuarios(){
		
		return repU.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET all Cacheable
	@Operation(description = "Retorna todos os usuarios existentes no Cache",
			summary = "Retorna todos os usuarios utilizando Caching",
			tags = {"Usuário"})
	@GetMapping(value = "/todas_cacheable")
	public List<UsuarioDTO> retonaTodosUsuariosCacheable(){
		
		return cacheU.findAll().stream().map(mapperInterface::toDTO).collect(Collectors.toList());
	}
	
	// GET By Id
	@Operation(description = "Retorna um usuario com base em um ID",
			summary = "Retorna um usuario com base em um ID",
			tags = {"Usuário"})
	@GetMapping(value = "/{id}")
	public UsuarioDTO retornaUsuarioPorId(@PathVariable Long id) {
		
		Optional<Usuario> op = cacheU.findById(id);
		
		if(op.isPresent()) {
			return mapperInterface.toDTO(op.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	//GET By Email
	@Operation(description = "Retorna um usuario com base em um Email",
			summary = "Retorna um usuario com base em um Email",
			tags = {"Usuário"})
	@GetMapping(value = "/usuario_por_email")
	public UsuarioDTO retornaUsuarioPorEmail(@RequestParam(value = "email") String email){
		
		Optional<Usuario> op = cacheU.findByEmail(email);
		
		if(op.isPresent()) {
			return mapperInterface.toDTO(op.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	// POST
	@Operation(description = "Esta operação possibilita a inserção de um novo item na tabela de usuarios",
			summary = "Inserir um novo usuario",
			tags = {"Usuário"})
	@PostMapping(value = "/inserir")
	public ResponseEntity<Usuario> inserirUsuario(@RequestBody @Valid Usuario usuario) {
		
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		
		repU.save(usuario);
		cacheU.limparCache();
				
		return ResponseEntity.ok(usuario);
	}
	
	// PUT
	@Operation(description = "Esta operação possibilita a atualização de um item na tabela de usuarios",
			summary = "Atualiza um novo usuario",
			tags = {"Usuário"})
	@PutMapping(value = "/atualizar/{id}")
	public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		
		Optional<Usuario> op = cacheU.findById(id);
		
		if(op.isPresent()) {
			Usuario usuario_antigo = op.get();
			usuario_antigo.setNome(usuario.getNome());
			usuario_antigo.setDataNascimento(usuario.getDataNascimento());
			usuario_antigo.setEmail(usuario.getEmail());
			usuario_antigo.setTelefone(usuario.getTelefone());
			
			if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
	            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
	            usuario_antigo.setSenha(senhaCriptografada);
	        }
			
			repU.save(usuario_antigo); 
	        cacheU.limparCache();
	        
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return usuario;
	}
	
	// DELETE
	@Operation(description = "Esta operação possibilita a exclusão de um item na tabela de usuarios",
			summary = "Exclui um novo usuario",
			tags = {"Usuário"})
	@DeleteMapping(value = "/excluir/{id}")
	public Usuario excluirUsuario(@PathVariable Long id) {
		
		Optional<Usuario> op = repU.findById(id);
		
		if (op.isPresent()) {
			Usuario usuario_remover = op.get();
			repU.delete(usuario_remover);
			cacheU.limparCache();	
			return usuario_remover;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
