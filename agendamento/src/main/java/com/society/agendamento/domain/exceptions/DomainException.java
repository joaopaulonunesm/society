package com.society.agendamento.domain.exceptions;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class DomainException extends RuntimeException {

    private final String codigo;
    private final Object[] parametros;

    public DomainException(DomainMensagem useCaseMensagem, Object... parametros) {
        super(useCaseMensagem.getCodigo() + " - " + Arrays.toString(parametros));
        this.codigo = useCaseMensagem.getCodigo();
        this.parametros = parametros;
    }
}
