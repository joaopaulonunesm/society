package com.society.enums;

public enum StatusAgendamento {

	AGUARDANDO_SOCIETY(1), CONFIRMADO(2), CANCELADO(3);

	private Integer id;

	private StatusAgendamento(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public static StatusAgendamento buscarPorId(Integer id) {

		for (StatusAgendamento status : values()) {
			if (status.getId() == id)
				return status;
		}

		return null;
	}
	
	public static StatusAgendamento buscarPorString(String confirmacao) {

		if (confirmacao.equalsIgnoreCase(CONFIRMADO.toString())) {
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
