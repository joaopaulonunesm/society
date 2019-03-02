package com.society.enums;

public enum StatusSociety {

	EM_ANALISE(1), ATIVO(2), INATIVO(3);
	
	private Integer id;

	private StatusSociety(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public static StatusSociety buscarPorId(Integer id) {

		for (StatusSociety status : values()) {
			if (status.getId() == id)
				return status;
		}

		return null;
	}
	
	public static StatusSociety buscarPorString(String confirmacao) {

		if(confirmacao.equalsIgnoreCase(ATIVO.toString())) {
			return ATIVO;
		} else if (confirmacao.equalsIgnoreCase(INATIVO.toString())) {
			return INATIVO;
		} else if (confirmacao.equalsIgnoreCase(EM_ANALISE.toString())) {
			return EM_ANALISE;
		} else {
			return null;
		}
		
	}
	
}
