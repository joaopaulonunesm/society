package com.society.agendamento.application.mapper;

import com.society.agendamento.domain.models.Agendamento;
import com.society.agendamento.application.dto.CriaAgendamentoRequest;
import com.society.agendamento.application.dto.AgendamentoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoMapper {

    public static Agendamento criaAgendamento(CriaAgendamentoRequest criaAgendamentoRequest){
        Agendamento agendamento = Agendamento.builder()
                .idSociety(criaAgendamentoRequest.getIdSociety())
                .dataInicio(criaAgendamentoRequest.getDataInicio())
                .dataFim(criaAgendamentoRequest.getDataInicio().plusHours(criaAgendamentoRequest.getQuantidadeHoras()))
                .nomeResponsavel(criaAgendamentoRequest.getNomeResponsavel())
                .celular(criaAgendamentoRequest.getCelular())
                .email(criaAgendamentoRequest.getEmail())
                .celularSecundario(criaAgendamentoRequest.getCelularSecundario())
                .observacao(criaAgendamentoRequest.getObservacao())
                .build();

        agendamento.definirIdSociety(criaAgendamentoRequest.getIdSociety());
        agendamento.definirStatusInicial();

        return agendamento;
    }

    public static AgendamentoResponse retornarAgendamentoResponse(Agendamento agendamento){
        return AgendamentoResponse.builder()
                .id(agendamento.getId())
                .status(agendamento.getStatus())
                .celular(agendamento.getCelular())
                .celularSecundario(agendamento.getCelularSecundario())
                .dataInicio(agendamento.getDataInicio())
                .email(agendamento.getEmail())
                .nomeResponsavel(agendamento.getNomeResponsavel())
                .observacao(agendamento.getObservacao())
                .dataFim(agendamento.getDataFim())
                .build();
    }

    public static List<AgendamentoResponse> retornarListaDeAgendamentoResponse(List<Agendamento> agendamentos){
        return agendamentos.stream().map((agendamento) -> retornarAgendamentoResponse(agendamento)).collect(Collectors.toList());
    }
}
