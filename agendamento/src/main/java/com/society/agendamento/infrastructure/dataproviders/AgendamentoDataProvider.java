package com.society.agendamento.infrastructure.dataproviders;

import com.society.agendamento.domain.gateway.AgendamentoGateway;
import com.society.agendamento.domain.entidades.Agendamento;
import com.society.agendamento.infrastructure.dataproviders.repository.AgendamentoEntity;
import com.society.agendamento.infrastructure.dataproviders.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.society.agendamento.infrastructure.mappers.AgendamentoEntityMapper.*;

@RequiredArgsConstructor
@Service
public class AgendamentoDataProvider implements AgendamentoGateway {

    private final AgendamentoRepository agendamentoRepository;

    @Override
    public Agendamento criar(Agendamento agendamento) {

        AgendamentoEntity agendamentoEntity = agendamentoParaEntity(agendamento);

        agendamentoEntity = agendamentoRepository.save(agendamentoEntity);

        agendamento.definirId(agendamentoEntity.getId());

        return agendamento;
    }

    @Override
    public Agendamento alterar(Agendamento agendamento) {
        return null;
    }

    @Override
    public Optional<Agendamento> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public Agendamento confirmar(Agendamento agendamento) {
        return null;
    }

    @Override
    public Agendamento cancelar(Agendamento agendamento) {
        return null;
    }

    @Override
    public List<Agendamento> agendamentosDoSocietyPorHorario(LocalDateTime dataInicio, LocalDateTime dataFim, Long idSociety) {

        List<AgendamentoEntity> agendamentosEntity = agendamentoRepository.findByDataInicioAndDataFimAndSociety(dataInicio, dataFim, idSociety);

        return listEntityParaAgendamento(agendamentosEntity);
    }

    @Override
    public List<Agendamento> agendamentosPorSociety(Long idSociety) {
        List<AgendamentoEntity> agendamentosEntity = agendamentoRepository.findByIdSocietyOrderByDataInicio(idSociety);

        return listEntityParaAgendamento(agendamentosEntity);
    }
}
