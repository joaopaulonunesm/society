package com.society.infrastructure.repositories.agendamento;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	@Query("select a from Agendamento a where a.dataInicio < ?1 and a.dataFim > ?1 or a.dataInicio < ?2 and a.dataFim >= ?2 and a.society.id = ?3")
	List<Agendamento> findByDataInicioAndDataFimAndSociety(LocalDateTime data, LocalDateTime dataFim, Long idSociety);
	
	List<Agendamento> findBySocietyIdOrderByDataInicio(Long idSociety);
	
}
