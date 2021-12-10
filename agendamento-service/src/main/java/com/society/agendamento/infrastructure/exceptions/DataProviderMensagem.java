package com.society.agendamento.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DataProviderMensagem {

    ERRO_COMUNICACAO_SOCIETY_SERVICE("agendamento.error.comunicacao-society-service");

    private String codigo;
}
