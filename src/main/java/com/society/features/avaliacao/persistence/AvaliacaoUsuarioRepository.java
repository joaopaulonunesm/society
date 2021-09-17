package com.society.features.avaliacao.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.features.avaliacao.domain.AvaliacaoUsuario;

public interface AvaliacaoUsuarioRepository extends JpaRepository<AvaliacaoUsuario, Long> {

	List<AvaliacaoUsuario> findByAgendamentoUsuarioId(Long idUsuario);

	Optional<AvaliacaoUsuario> findByAgendamentoId(Long id);

}
