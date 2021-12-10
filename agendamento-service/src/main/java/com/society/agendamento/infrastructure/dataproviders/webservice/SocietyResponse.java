package com.society.agendamento.infrastructure.dataproviders.webservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.society.agendamento.domain.enums.StatusSociety;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocietyResponse implements Serializable {

    private static final long serialVersionUID = -3173007618060815967L;

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
