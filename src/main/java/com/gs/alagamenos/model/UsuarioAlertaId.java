package com.gs.alagamenos.model;

import java.io.Serializable;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Schema(description = "Esta classe irá representar a entidade de junção dos ID para Alertas de Usuários")
@Embeddable
@Data
public class UsuarioAlertaId implements Serializable {

	private Long usuarioId;
	private Long alertaId;

	public UsuarioAlertaId() {}

	public UsuarioAlertaId(Long usuarioId, Long alertaId) {
		this.usuarioId = usuarioId;
		this.alertaId = alertaId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UsuarioAlertaId)) return false;
		UsuarioAlertaId that = (UsuarioAlertaId) o;
		return Objects.equals(usuarioId, that.usuarioId) &&
			   Objects.equals(alertaId, that.alertaId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuarioId, alertaId);
	}
}