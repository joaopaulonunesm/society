package com.society.gerenciamento.application.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class SocietyRequest {

    @NotBlank(message = "{society.info.nome}")
    private String nome;

    private String descricao;

    @NotNull(message = "{society.info.valor-hora-null}")
    @Positive(message = "{society.info.valor-hora}")
    private BigDecimal valorHora;

    @NotBlank(message = "{society.info.nome-responsavel}")
    private String nomeResponsavel;

    @NotBlank(message = "{society.info.celular}")
    @Pattern(regexp = "^(\\d{2})(\\d{5})(\\d{4})$", message = "{society.info.celular}")
    private String celular;

    @NotBlank(message = "{society.info.telefone}")
    @Pattern(regexp = "^(\\d{2})(\\d{4})(\\d{4})$", message = "{society.info.telefone}")
    private String telefone;

    @Email(message = "{society.info.email}")
    private String email;

    @NotBlank(message = "{society.info.endereco}")
    private String endereco;

    @NotBlank(message = "{society.info.cep}")
    private String cep;

    private String observacao;

    private String urlFoto;

    @NotNull(message = "{society.info.quantidade-campo-null}")
    @Positive(message = "{society.info.quantidade-campo}")
    private Long quantidadeCampos;
}
