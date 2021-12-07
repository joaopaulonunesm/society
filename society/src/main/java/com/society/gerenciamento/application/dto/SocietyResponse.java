package com.society.gerenciamento.application.dto;

import com.society.gerenciamento.domain.enums.StatusSociety;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class SocietyResponse {

    private Long id;
    private StatusSociety status;
    private String nome;
    private String nomeUrl;
    private String descricao;
    private BigDecimal valorHora;
    private String nomeResponsavel;
    private String celular;
    private String telefone;
    private String endereco;
    private String cep;
    private String observacao;
    private String urlFoto;
    private Long quantidadeCampos;
}
