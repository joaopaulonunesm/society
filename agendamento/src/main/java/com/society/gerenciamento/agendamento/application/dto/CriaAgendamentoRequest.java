package com.society.gerenciamento.agendamento.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CriaAgendamentoRequest {

    private Long idSociety;
    private LocalDateTime dataInicio;
    private Long quantidadeHoras;
}
