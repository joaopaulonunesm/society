package com.society.agendamento.infrastructure.dataproviders;

import com.society.agendamento.domain.gateways.AgendamentoGateway;
import com.society.agendamento.domain.models.Agendamento;
import com.society.agendamento.infrastructure.dataproviders.repository.AgendamentoEntity;
import com.society.agendamento.infrastructure.dataproviders.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.society.agendamento.infrastructure.mappers.AgendamentoEntityMapper.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class AgendamentoDataProvider implements AgendamentoGateway {

    private final AgendamentoRepository agendamentoRepository;

    @Override
    public Agendamento salvar(Agendamento agendamento) {
        log.info("M=salvar, message=Salvando agendamento no banco de dados, agendamento={}", agendamento);

        AgendamentoEntity agendamentoEntity = agendamentoParaEntity(agendamento);

        agendamentoEntity = agendamentoRepository.save(agendamentoEntity);

        agendamento.definirId(agendamentoEntity.getId());

        log.info("M=salvar, message=Agendamento salvo com sucesso, agendamento={}", agendamento);
        return agendamento;
    }

    @Override
    public Optional<Agendamento> buscarPorId(Long id) {
        log.info("M=buscarPorId, message=Buscando agendamento por ID no banco de dados, id={}", id);

        Optional<AgendamentoEntity> agendamentoEntity = agendamentoRepository.findById(id);

        if(agendamentoEntity.isEmpty()){
            return Optional.empty();
        }

        Agendamento agendamento = entityParaAgendamento(agendamentoEntity.get());

        log.info("M=buscarPorId, message=Agendamento por ID encontrado no banco de dados, id={}, agendamento={}", id, agendamento);
        return Optional.of(agendamento);
    }

    @Override
    public List<Agendamento> agendamentosDoSocietyPorHorario(LocalDateTime dataInicio, LocalDateTime dataFim, Long idSociety) {
        log.info("M=agendamentosDoSocietyPorHorario, message=Buscando agendamento por horario e society no banco de dados, dataInicio={}, dataFim={}, idSociety={}", dataInicio, dataFim, idSociety);

        List<AgendamentoEntity> agendamentosEntity = agendamentoRepository.findByDataInicioAndDataFimAndSociety(dataInicio, dataFim, idSociety);

        List<Agendamento> agendamentos = listEntityParaAgendamento(agendamentosEntity);

        log.info("M=agendamentosDoSocietyPorHorario, message=Agendamento por horario e society encontrado no banco de dados, dataInicio={}, dataFim={}, idSociety={}, agendamentos.size={}", dataInicio, dataFim, idSociety, agendamentos.size());
        return agendamentos;
    }

    @Override
    public List<Agendamento> agendamentosPorSociety(Long idSociety) {
        log.info("M=agendamentosPorSociety, message=Buscando agendamentos por society no banco de dados, idSociety={}", idSociety);

        List<AgendamentoEntity> agendamentosEntity = agendamentoRepository.findByIdSocietyOrderByDataInicio(idSociety);

        List<Agendamento> agendamentos = listEntityParaAgendamento(agendamentosEntity);

        log.info("M=agendamentosPorSociety, message=Agendamentos por society encontrado no banco de dados, idSociety={}, agendamentos.size={}", idSociety, agendamentos.size());
        return agendamentos;
    }
}
