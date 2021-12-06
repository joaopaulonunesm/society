package com.society.gerenciamento.agendamento.domain.gateway;

import com.society.gerenciamento.agendamento.domain.entidades.Society;

import java.util.Optional;

public interface SocietyGateway {
    Optional<Society> buscarPorId(Long society);
}
