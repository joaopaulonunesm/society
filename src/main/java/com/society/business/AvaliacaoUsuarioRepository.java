package com.society.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.model.AvaliacaoUsuario;

public interface AvaliacaoUsuarioRepository extends JpaRepository<AvaliacaoUsuario, Long> {

	List<AvaliacaoUsuario> findByAgendamentoUsuarioId(Long idUsuario);

	AvaliacaoUsuario findByAgendamentoId(Long id);

}
