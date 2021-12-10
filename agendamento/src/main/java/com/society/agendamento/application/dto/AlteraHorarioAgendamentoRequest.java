package com.society.agendamento.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@ToString
public class AlteraHorarioAgendamentoRequest {

    @NotNull(message = "{agendamento.info.data-inicio}")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInicio;

    @NotNull(message = "{agendamento.info.quantidade-horas}")
    @Positive(message = "{agendamento.info.quantidade-horas}")
    private Long quantidadeHoras;
}
