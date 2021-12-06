package com.society.gerenciamento.agendamento.application.mapper;

import com.society.gerenciamento.agendamento.application.dto.CriaAgendamentoRequest;
import com.society.gerenciamento.agendamento.application.dto.AgendamentoResponse;
import com.society.gerenciamento.agendamento.domain.entidades.Agendamento;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapper {

    public AgendamentoResponse agendamentoParaResponse(Agendamento agendamento){
        return AgendamentoResponse.builder().build();
    }

    public Agendamento requestParaAgendamento(CriaAgendamentoRequest criaAgendamentoRequest){
        return Agendamento.builder().build();
    }
}
