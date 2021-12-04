package com.society.usecases.enums;

public enum StatusSociety {

	ATIVO, INATIVO, EM_ANALISE;
	
	public static StatusSociety getPorString(String confirmacao) {

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
