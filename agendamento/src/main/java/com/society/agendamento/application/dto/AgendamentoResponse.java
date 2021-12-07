package com.society.agendamento.application.dto;

import com.society.agendamento.domain.enums.StatusAgendamento;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AgendamentoResponse {

    private Long id;
    private StatusAgendamento status;
}
