package com.society.gerenciamento.agendamento.domain.enums;

public enum StatusAgendamento {

	CONFIRMADO, CANCELADO, AGUARDANDO_SOCIETY;

	public static StatusAgendamento getPorString(String confirmacao) {

		if(confirmacao.equalsIgnoreCase(CONFIRMADO.toString())) {
			return CONFIRMADO;
		} else if (confirmacao.equalsIgnoreCase(CANCELADO.toString())) {
			return CANCELADO;
		} else if (confirmacao.equalsIgnoreCase(AGUARDANDO_SOCIETY.toString())) {
			return AGUARDANDO_SOCIETY;
		} else {
			return null;
		}
	}
}
