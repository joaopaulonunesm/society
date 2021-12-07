package com.society.agendamento.domain.gateway;

import com.society.agendamento.domain.entidades.Society;

import java.util.Optional;

public interface SocietyGateway {
    Optional<Society> buscarPorId(Long idSociety);
}
