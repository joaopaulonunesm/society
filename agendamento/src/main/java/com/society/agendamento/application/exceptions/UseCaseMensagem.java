package com.society.agendamento.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UseCaseMensagem {

    SOCIETY_NAO_CADASTRADO("agendamento.error.society-nao-cadastrado"),
    DATA_INICIO_MENOR_QUE_DATA_ATUAL("agendamento.error.data-inicio-menor-que-data-atual"),
    NAO_EXISTE_CAMPO_DISPONIVEL("agendamento.error.nao-existe-campo-disponivel"),
    AGENDAMENTO_NAO_CADASTRADO("agendamento.error.nao-existe-agendamento-cadastrado"),
    AGENDAMENTO_JA_POSSUI_ESSE_HORARIO("agendamento.error.agendamento-ja-possui-esse-horario"),
    AGENDAMENTO_PASSADO("agendamento.error.agendamento-passado"),
    SOCIETY_INATIVO("agendamento.error.society-inativo");

    private String codigo;
}
