package com.society.gerenciamento.agendamento.domain.gateway;

import com.society.gerenciamento.agendamento.domain.entidades.Agendamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoGateway {

    Agendamento criar(Agendamento agendamento);

    Agendamento alterar(Agendamento agendamento);

    Optional<Agendamento> buscarPorId(Long id);

    List<Agendamento> agendamentosDoSocietyPorHorario(LocalDateTime dataInicio, Long idSociety);

    Agendamento confirmar(Agendamento agendamento);

    Agendamento cancelar(Agendamento agendamento);
}
