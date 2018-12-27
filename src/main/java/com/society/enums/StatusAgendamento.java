package com.society.enums;

public enum StatusAgendamento {

	AGUARDANDO_SOCIETY, CONFIRMADO, CANCELADO;

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
