package com.gs.alagamenos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Esta classe irá representar a entidade UsuarioAlerta")
@Data
@Entity
@Table(name = "USUARIO_ALERTA")
public class UsuarioAlerta {

	@Schema(description = "Este atributo representa a chave composta: ID do usuário e ID do alerta")
	@EmbeddedId
	private UsuarioAlertaId id;

	@ManyToOne
	@MapsId("usuarioId")
	@JoinColumn(name = "USUARIO_ID", nullable = false)
	@NotNull(message = "Não é permitido associar alerta sem usuário")
	@Schema(description = "Este atributo representa a instância de um usuário.")
	private Usuario usuario;

	@ManyToOne
	@MapsId("alertaId")
	@JoinColumn(name = "ALERTA_ID", nullable = false)
	@NotNull(message = "Não é permitido associar usuário sem alerta")
	@Schema(description = "Este atributo representa a instância de um alerta.")
	private Alerta alerta;
}
