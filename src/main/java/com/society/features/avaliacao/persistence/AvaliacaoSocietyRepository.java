package com.society.features.avaliacao.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.features.avaliacao.domain.AvaliacaoSociety;

public interface AvaliacaoSocietyRepository extends JpaRepository<AvaliacaoSociety, Long> {

	List<AvaliacaoSociety> findByAgendamentoSocietyId(Long idSociety);

	Optional<AvaliacaoSociety> findByAgendamentoId(Long id);

}
