package com.society.usecases.exceptions;

import lombok.Getter;

@Getter
public class UseCaseException extends RuntimeException {

	private String codigo;
	private Object[] parametros;

	public UseCaseException(String codigo, Object... parametros) {
		super(codigo + " - " + parametros);
		this.codigo = codigo;
		this.parametros = parametros;
	}

}
