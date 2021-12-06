package com.society.gerenciamento.agendamento.infrastructure.mappers;

import com.society.gerenciamento.agendamento.domain.entidades.Agendamento;
import com.society.gerenciamento.agendamento.infrastructure.dataproviders.AgendamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapper {

    public AgendamentoEntity agendamentoParaEntity(Agendamento agendamento) {
        return AgendamentoEntity.builder().build();
    }

    public Agendamento entityParaAgendamento(AgendamentoEntity agendamentoEntity) {
        return Agendamento.builder().build();
    }
}
