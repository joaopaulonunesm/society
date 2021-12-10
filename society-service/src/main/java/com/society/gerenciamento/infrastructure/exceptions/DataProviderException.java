package com.society.gerenciamento.infrastructure.exceptions;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class DataProviderException extends RuntimeException {

    private final String codigo;
    private final Object[] parametros;

    public DataProviderException(DataProviderMensagem useCaseMensagem, Object... parametros) {
        super(useCaseMensagem.getCodigo() + " - " + Arrays.toString(parametros));
        this.codigo = useCaseMensagem.getCodigo();
        this.parametros = parametros;
    }
}
