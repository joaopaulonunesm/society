package com.society.gerenciamento.api.middlewares;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(NON_NULL)
public class ErrorResponse {

    private String codigo;
    private String campo;
    private String mensagem;

    public ErrorResponse(String codigo, String mensagem){
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
}