package com.society.enums;

public enum StatusSociety {

	EM_ANALISE, ATIVO, INATIVO, ;
	
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
