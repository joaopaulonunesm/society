package com.society.agendamento.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CriaAgendamentoRequest {

    @NotNull(message = "{agendamento.info.id-society}")
    @Positive(message = "{agendamento.info.id-society}")
    private Long idSociety;

    @NotNull(message = "{agendamento.info.data-inicio}")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInicio;

    @NotNull(message = "{agendamento.info.quantidade-horas}")
    @Positive(message = "{agendamento.info.quantidade-horas}")
    private Long quantidadeHoras;

    @NotBlank(message = "{agendamento.info.nome-responsavel}")
    private String nomeResponsavel;

    @NotBlank(message = "{agendamento.info.celular}")
    @Pattern(regexp = "^(\\d{2})(\\d{5})(\\d{4})$", message = "{agendamento.info.celular}")
    private String celular;

    @Pattern(regexp = "^(\\d{2})(\\d{5})(\\d{4})$", message = "{agendamento.info.celular-secundario}")
    private String celularSecundario;

    @Email(message = "{society.info.email}")
    private String email;

    private String observacao;
}
