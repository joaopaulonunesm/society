package com.society.agendamento.domain.gateways;

import com.society.agendamento.domain.models.Society;

import java.util.Optional;

public interface SocietyGateway {
    Optional<Society> buscarPorId(Long idSociety);
}
