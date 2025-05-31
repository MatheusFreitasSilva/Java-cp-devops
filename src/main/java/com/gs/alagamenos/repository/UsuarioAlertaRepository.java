package com.gs.alagamenos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gs.alagamenos.model.UsuarioAlerta;
import com.gs.alagamenos.model.UsuarioAlertaId;

public interface UsuarioAlertaRepository extends JpaRepository<UsuarioAlerta, UsuarioAlertaId>{

}
