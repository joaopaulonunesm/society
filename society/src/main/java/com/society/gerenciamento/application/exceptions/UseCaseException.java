package com.society.gerenciamento.application.exceptions;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class UseCaseException extends RuntimeException {

	private final String codigo;
	private final Object[] parametros;

	public UseCaseException(UseCaseMensagem useCaseMensagem, Object... parametros) {
		super(useCaseMensagem.getCodigo() + " - " + Arrays.toString(parametros));
		this.codigo = useCaseMensagem.getCodigo();
		this.parametros = parametros;
	}
}
