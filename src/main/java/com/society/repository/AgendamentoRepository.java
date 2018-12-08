package com.society.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.society.model.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	@Query("select a from Agendamento a where a.dataInicio < ?1 and a.dataFim > ?1 or a.dataInicio < ?2 and a.dataFim >= ?2 and a.society.id = ?3")
	public List<Agendamento> findByDataInicioAndDataFimAndSociety(Date data, Date dataFim, Long idSociety);

	
	@Query("select a from Agendamento a where a.society.id = ?1 and substring(dataInicio, 1,10) = substring(?2, 1,10)")
	public List<Agendamento> findByDataAndSociety(Long id, Date data);
	
}
