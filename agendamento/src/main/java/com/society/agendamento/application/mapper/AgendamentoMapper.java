package com.society.agendamento.application.mapper;

import com.society.agendamento.domain.entidades.Agendamento;
import com.society.agendamento.domain.entidades.Society;
import com.society.agendamento.domain.enums.StatusAgendamento;
import com.society.agendamento.application.dto.CriaAgendamentoRequest;
import com.society.agendamento.application.dto.AgendamentoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoMapper {

    public static AgendamentoResponse agendamentoParaResponse(Agendamento agendamento){
        return AgendamentoResponse.builder()
                .id(agendamento.getId())
                .status(agendamento.getStatus())
                .build();
    }

    public static Agendamento requestParaAgendamento(CriaAgendamentoRequest criaAgendamentoRequest, Society society){
        return Agendamento.builder()
                .society(society)
                .dataInicio(criaAgendamentoRequest.getDataInicio())
                .dataFim(criaAgendamentoRequest.getDataInicio().plusHours(criaAgendamentoRequest.getQuantidadeHoras()))
                .nomeResponsavel(criaAgendamentoRequest.getNomeResponsavel())
                .celular(criaAgendamentoRequest.getCelular())
                .email(criaAgendamentoRequest.getEmail())
                .celularSecundario(criaAgendamentoRequest.getCelularSecundario())
                .observacao(criaAgendamentoRequest.getObservacao())
                .build();
    }

    public static List<AgendamentoResponse> listAgendamentoParaResponse(List<Agendamento> agendamentos){
        return agendamentos.stream().map((agendamento) -> agendamentoParaResponse(agendamento)).collect(Collectors.toList());
    }
}
