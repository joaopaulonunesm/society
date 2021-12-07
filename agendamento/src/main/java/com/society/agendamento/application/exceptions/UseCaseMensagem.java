package com.society.agendamento.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UseCaseMensagem {

    SOCIETY_NAO_CADASTRADO("agendamento.error.society-nao-cadastrado"),
    DATA_INICIO_MENOR_QUE_DATA_ATUAL("agendamento.error.data-inicio-menor-que-data-atual"),
    NAO_EXISTE_CAMPO_DISPONIVEL("agendamento.error.nao-existe-campo-disponivel"),
    AGENDAMENTO_NAO_CADASTRADO("agendamento.error.nao-existe-agendamento-cadastrado");

    private String codigo;
}
