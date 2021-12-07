package com.society.agendamento.domain.gateway;

import com.society.agendamento.domain.entidades.Agendamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoGateway {

    Agendamento criar(Agendamento agendamento);

    Agendamento alterar(Agendamento agendamento);

    Optional<Agendamento> buscarPorId(Long id);

    Agendamento confirmar(Agendamento agendamento);

    Agendamento cancelar(Agendamento agendamento);

    List<Agendamento> agendamentosDoSocietyPorHorario(LocalDateTime dataInicio, LocalDateTime dataFim, Long idSociety);

    List<Agendamento> agendamentosPorSociety(Long idSociety);
}
