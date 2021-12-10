package com.society.agendamento.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DomainMensagem {

    SITUACAO_SOCIETY_ATUAL("society.error.status-atual");

    private String codigo;
}
