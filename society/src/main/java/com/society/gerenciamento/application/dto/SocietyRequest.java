package com.society.gerenciamento.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SocietyRequest {

    @NotNull
    private String nome;
    private String descricao;
    @NotNull
    @Positive
    private BigDecimal valorHora;
    @NotNull
    private String nomeResponsavel;
    @NotNull
    private String celular;
    private String telefone;
    @NotNull
    private String endereco;
    @NotNull
    private String cep;
    private String observacao;
    private String urlFoto;
    @NotNull
    private Long quantidadeCampos;
}
