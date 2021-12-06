package com.society.gerenciamento.agendamento.api.middlewares;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorResponse {

    private String codigo;
    private String mensagem;
}
