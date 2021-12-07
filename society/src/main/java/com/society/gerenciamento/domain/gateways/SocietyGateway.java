package com.society.gerenciamento.domain.gateways;

import com.society.gerenciamento.domain.entities.Society;

import java.util.List;
import java.util.Optional;

public interface SocietyGateway {
    Society criar(Society society);

    Society alterar(Society society);

    List<Society> buscarTodos();

    Optional<Society> buscarPorId(Long id);

    Optional<Society> buscaPorNomeUrl(String nomeUrl);
}
