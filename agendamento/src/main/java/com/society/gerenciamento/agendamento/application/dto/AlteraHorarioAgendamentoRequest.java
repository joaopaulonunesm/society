package com.society.gerenciamento.agendamento.application.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AlteraHorarioAgendamentoRequest {

    private LocalDateTime dataInicio;
    private Long quantidadeHoras;
}
