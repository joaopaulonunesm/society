package com.society.gerenciamento.domain.gateways;

import com.society.gerenciamento.domain.models.Society;

import java.util.List;
import java.util.Optional;

public interface SocietyGateway {
    Society salvar(Society society);

    List<Society> buscarTodos();

    Optional<Society> buscarPorId(Long id);

    Optional<Society> buscaPorNomeUrl(String nomeUrl);
}
