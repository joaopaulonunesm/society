package com.society.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.society.model.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	@Query("select a from Agendamento a where ((a.dataInicio < ?1 and a.dataFim > ?1) or (a.dataInicio < ?2 and a.dataFim >= ?2)) and a.society.id = ?3 and a.statusAgendamento = ?4")
	public List<Agendamento> findByDataInicioAndDataFimAndSocietyAndStatusAgendamento(Date data, Date dataFim, Long idSociety, Integer statusAgendamento);
	
	public List<Agendamento> findBySocietyIdOrderByDataInicio(Long idSociety);

	public List<Agendamento> findByUsuarioIdOrderByDataInicio(Long id);

	public Agendamento findByUsuarioIdAndDataInicioAndSocietyId(Long idUsuario, Date dataAgendamento, Long idSociety);
	
}
