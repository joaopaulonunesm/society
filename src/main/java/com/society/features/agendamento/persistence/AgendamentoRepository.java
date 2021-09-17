package com.society.features.agendamento.persistence;


import com.society.features.agendamento.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	@Query("select a from Agendamento a where ((a.dataInicio < ?1 and a.dataFim > ?1) or (a.dataInicio < ?2 and a.dataFim >= ?2)) and a.society.id = ?3 and a.statusAgendamento = ?4")
	List<Agendamento> findByDataInicioAndDataFimAndSocietyAndStatusAgendamento(LocalDateTime dataInicio, LocalDateTime dataFim, Long idSociety, Integer statusAgendamento);
	
	List<Agendamento> findBySocietyIdOrderByDataInicio(Long idSociety);

	List<Agendamento> findByUsuarioIdOrderByDataInicio(Long id);

	Optional<Agendamento> findByUsuarioIdAndDataInicioAndSocietyId(Long idUsuario, LocalDateTime dataAgendamento, Long idSociety);
}
