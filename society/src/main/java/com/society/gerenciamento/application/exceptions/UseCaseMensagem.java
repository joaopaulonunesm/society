package com.society.gerenciamento.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UseCaseMensagem {

    SOCIETY_NAO_CADASTRADO("society.error.society-nao-cadastrado"),
    SOCIETY_JA_EXISTENTE("society.error.society-ja-existente");

    private String codigo;
}
