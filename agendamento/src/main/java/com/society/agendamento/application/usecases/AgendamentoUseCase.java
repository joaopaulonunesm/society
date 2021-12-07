package com.society.agendamento.application.usecases;

import com.society.agendamento.application.dto.CriaAgendamentoRequest;
import com.society.agendamento.application.exceptions.UseCaseException;
import com.society.agendamento.application.exceptions.UseCaseMensagem;
import com.society.agendamento.application.mapper.AgendamentoMapper;
import com.society.agendamento.domain.entidades.Agendamento;
import com.society.agendamento.domain.entidades.Society;
import com.society.agendamento.domain.gateway.AgendamentoGateway;
import com.society.agendamento.domain.gateway.SocietyGateway;
import com.society.agendamento.application.dto.AgendamentoResponse;
import com.society.agendamento.application.dto.AlteraHorarioAgendamentoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;
    private final SocietyGateway societyGateway;

    public AgendamentoResponse agendar(CriaAgendamentoRequest criaAgendamentoRequest) {

        if (criaAgendamentoRequest.getDataInicio().isBefore(LocalDateTime.now())) {
            throw new UseCaseException(UseCaseMensagem.DATA_INICIO_MENOR_QUE_DATA_ATUAL);
        }

        Society society = societyGateway.buscarPorId(criaAgendamentoRequest.getIdSociety())
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        Agendamento agendamento = AgendamentoMapper.requestParaAgendamento(criaAgendamentoRequest, society);

        agendamento.definirStatusInicial();

        List<Agendamento> agendamentos = agendamentoGateway.agendamentosDoSocietyPorHorario(agendamento.getDataInicio(), agendamento.getDataFim(), society.getId());

        if (!agendamentos.isEmpty() && agendamentos.size() >= society.getQuantidadeCampos()) {
            throw new UseCaseException(UseCaseMensagem.NAO_EXISTE_CAMPO_DISPONIVEL);
        }

        agendamento = agendamentoGateway.criar(agendamento);

        return AgendamentoMapper.agendamentoParaResponse(agendamento);
    }

    public AgendamentoResponse alterarHorario(Long id, AlteraHorarioAgendamentoRequest alteraHorarioAgendamentoRequest) {

        Agendamento agendamento = agendamentoGateway.buscarPorId(id)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.AGENDAMENTO_NAO_CADASTRADO));

        agendamento.definirDataInicio(alteraHorarioAgendamentoRequest.getDataInicio());
        agendamento.definirDataFim(alteraHorarioAgendamentoRequest.getQuantidadeHoras());

        if (agendamento.getDataInicio().isBefore(LocalDateTime.now())) {
            throw new UseCaseException(UseCaseMensagem.DATA_INICIO_MENOR_QUE_DATA_ATUAL);
        }

        List<Agendamento> agendamentos = agendamentoGateway.agendamentosDoSocietyPorHorario(agendamento.getDataInicio(), agendamento.getDataInicio(), agendamento.getSociety().getId());

        if (!agendamentos.isEmpty() && agendamentos.size() >= agendamento.getSociety().getQuantidadeCampos()) {
            throw new UseCaseException(UseCaseMensagem.NAO_EXISTE_CAMPO_DISPONIVEL);
        }

        agendamentoGateway.alterar(agendamento);

        return AgendamentoMapper.agendamentoParaResponse(agendamento);
    }

    public Optional<AgendamentoResponse> buscarPorId(Long id) {

        Optional<Agendamento> agendamento = agendamentoGateway.buscarPorId(id);

        if(agendamento.isEmpty()){
            return Optional.empty();
        }

        AgendamentoResponse agendamentoResponse = AgendamentoMapper.agendamentoParaResponse(agendamento.get());

        return Optional.of(agendamentoResponse);
    }

    public AgendamentoResponse confirmar(Long id) {

        Agendamento agendamento = agendamentoGateway.buscarPorId(id)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.AGENDAMENTO_NAO_CADASTRADO));

        agendamento.confirmarAgendamento();

        agendamentoGateway.confirmar(agendamento);

        return AgendamentoMapper.agendamentoParaResponse(agendamento);
    }

    public AgendamentoResponse cancelar(Long id) {

        Agendamento agendamento = agendamentoGateway.buscarPorId(id)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.AGENDAMENTO_NAO_CADASTRADO));

        agendamento.cancelar();

        agendamentoGateway.cancelar(agendamento);

        return AgendamentoMapper.agendamentoParaResponse(agendamento);
    }

    public List<AgendamentoResponse> buscarPorSociety(Long idSociety) {

        List<Agendamento> agendamentos = agendamentoGateway.agendamentosPorSociety(idSociety);

        return AgendamentoMapper.listAgendamentoParaResponse(agendamentos);
    }
}