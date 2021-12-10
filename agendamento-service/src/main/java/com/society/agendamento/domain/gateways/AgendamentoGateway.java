package com.society.agendamento.domain.gateways;

import com.society.agendamento.domain.models.Agendamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoGateway {

    Agendamento salvar(Agendamento agendamento);

    Optional<Agendamento> buscarPorId(Long id);

    List<Agendamento> agendamentosDoSocietyPorHorario(LocalDateTime dataInicio, LocalDateTime dataFim, Long idSociety);

    List<Agendamento> agendamentosPorSociety(Long idSociety);
}
