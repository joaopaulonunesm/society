package com.society.agendamento.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(NON_NULL)
public class ErroResponse {

    private String codigo;
    private String campo;
    private String mensagem;

    public ErroResponse(String codigo, String mensagem){
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
}
