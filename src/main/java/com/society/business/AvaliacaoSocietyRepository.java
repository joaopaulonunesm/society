package com.society.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.model.AvaliacaoSociety;

public interface AvaliacaoSocietyRepository extends JpaRepository<AvaliacaoSociety, Long> {

	List<AvaliacaoSociety> findByAgendamentoSocietyId(Long idSociety);

	AvaliacaoSociety findByAgendamentoId(Long id);

}
