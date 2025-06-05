package com.gs.alagamenos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gs.alagamenos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("from Usuario u where u.email = :email")
	public Optional<Usuario> findByEmail(String email);

}
