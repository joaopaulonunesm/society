package com.society.gerenciamento.domain.exceptions;

import com.society.gerenciamento.application.exceptions.UseCaseMensagem;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class DomainException extends RuntimeException {

    private final String codigo;
    private final Object[] parametros;

    public DomainException(DomainMensagem domainMensagem, Object... parametros) {
        super(domainMensagem.getCodigo() + " - " + Arrays.toString(parametros));
        this.codigo = domainMensagem.getCodigo();
        this.parametros = parametros;
    }
}
