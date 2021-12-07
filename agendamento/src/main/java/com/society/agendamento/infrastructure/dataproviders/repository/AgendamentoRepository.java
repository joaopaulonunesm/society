package com.society.agendamento.infrastructure.dataproviders.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long>{

	@Query("select a from agendamento a where a.dataInicio < ?1 and a.dataFim > ?1 or a.dataInicio < ?2 and a.dataFim >= ?2 and a.idSociety = ?3")
	List<AgendamentoEntity> findByDataInicioAndDataFimAndSociety(LocalDateTime dataInicio, LocalDateTime dataFim, Long idSociety);
	
	List<AgendamentoEntity> findByIdSocietyOrderByDataInicio(Long idSociety);

}
