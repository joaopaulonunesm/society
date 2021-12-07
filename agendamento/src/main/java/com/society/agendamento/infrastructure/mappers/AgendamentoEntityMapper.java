package com.society.agendamento.infrastructure.mappers;

import com.society.agendamento.domain.entidades.Agendamento;
import com.society.agendamento.infrastructure.dataproviders.repository.AgendamentoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoEntityMapper {

    public static AgendamentoEntity agendamentoParaEntity(Agendamento agendamento) {
        return AgendamentoEntity.builder()
                .id(agendamento.getId())
                .dataInicio(agendamento.getDataInicio())
                .dataFim(agendamento.getDataFim())
                .email(agendamento.getEmail())
                .celular(agendamento.getCelular())
                .celularSecundario(agendamento.getCelularSecundario())
                .idSociety(agendamento.getSociety().getId())
                .nomeResponsavel(agendamento.getNomeResponsavel())
                .observacao(agendamento.getObservacao())
                .status(agendamento.getStatus())
                .build();
    }

    public static List<Agendamento> listEntityParaAgendamento(List<AgendamentoEntity> agendamentosEntity) {
        return agendamentosEntity.stream()
                .map((agendamentoEntity) -> entityParaAgendamento(agendamentoEntity))
                .collect(Collectors.toList());
    }

    private static Agendamento entityParaAgendamento(AgendamentoEntity agendamentoEntity) {
        return Agendamento.builder()
                .id(agendamentoEntity.getId())
                .dataInicio(agendamentoEntity.getDataInicio())
                .dataFim(agendamentoEntity.getDataFim())
                .email(agendamentoEntity.getEmail())
                .celular(agendamentoEntity.getCelular())
                .celularSecundario(agendamentoEntity.getCelularSecundario())
                .nomeResponsavel(agendamentoEntity.getNomeResponsavel())
                .observacao(agendamentoEntity.getObservacao())
                .status(agendamentoEntity.getStatus())
                .build();
    }
}
