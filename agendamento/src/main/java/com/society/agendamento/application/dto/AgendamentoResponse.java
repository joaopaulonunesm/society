package com.society.agendamento.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.society.agendamento.domain.enums.StatusAgendamento;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class AgendamentoResponse {

    private Long id;
    private StatusAgendamento status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFim;

    private String nomeResponsavel;
    private String celular;
    private String celularSecundario;
    private String email;
    private String observacao;
}
