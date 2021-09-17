package com.society.features.agendamento.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum StatusAgendamento {

	AGUARDANDO_SOCIETY(1), CONFIRMADO(2), CANCELADO(3);

	private Integer id;

	public static StatusAgendamento buscarPorId(Integer id) {
		for (StatusAgendamento status : values()) {
			if (status.getId() == id)
				return status;
		}
		return null;
	}
}
